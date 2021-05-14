import React, { useEffect, useState, useContext } from 'react';
import axios from 'axios';
import { APIConfig } from '../../store/API-Config';
import { Link } from 'react-router-dom';
import Review from '../../components/Review/Review';
import store from "../../store/store";
import { CartInfo } from "../../store/AppContext";
import { SET_CART } from "../../constants/constants";
import { useDispatch } from "react-redux";

export default function Products(props) {

  const dispatch = useDispatch();
  const APIs = useContext(APIConfig);
  const productAPI = APIs.productAPI;
  const productId = props.match.params.id;
  const [product, setProduct] = useState([]);
  const [qty, setQty] = useState(1);
  const state = store.getState();

  const { cartInfo, setCartInfo } = useContext(CartInfo);
  const [dataResponse, setDataResponse] = useState(cartInfo);

  useEffect(() => {
    const fecthData = async () => {
      try {
        const { data } = await axios.get(productAPI + '/' + productId);
        setProduct(data);
        // console.log(product);
      } catch (err) {
        console.log(err);
      }
    };
    fecthData();
  }, []);

  useEffect(() => {
    setCartInfo(dataResponse);

  }, [dataResponse]);

  let headers = {
    'Access-Control-Allow-Origin': '*',
    'Authorization': 'Bearer ' + state.oAuthToken,
  }

  const addToCartHandler = () => {
    // props.history.push(`/shoppingcarts/${productId}?qty=${qty}`);
    if (cartInfo) {
      let data = {
        "product": { "id": product.id },
        "quantity": qty,
        "price": product.price,
        "lineTotal": product.price * qty
      }

      console.log("cartid:" + cartInfo.id);
      axios.post(APIs.cartAPI + "/" + cartInfo.id + "/cartlines", data, { headers })
        .then(response => {
          if (response.data) {
            const info = JSON.stringify(response.data);
            dispatch({
              type: SET_CART,
              payload: info
            })
            setDataResponse(response.data);
          }

        }).catch(error => {
          alert(error.message);
        });
    } else {
      alert("create new cart");
      // get current buyer
      axios(APIs.userAPI + "/mybuyerinfo", { headers })
        .then(response => {
          if (response.data) {
            let buyerId = response.data.id;
            //create new CartInfo
            let data = {
              "totalQuantity": 0,
              "totalMoney": 0,
              "buyer": { "id": buyerId }
            }
            axios.post(APIs.cartAPI, data, { headers })
              .then(response => {
                if (response.data) {
                  const cart = JSON.stringify(response.data);
                  dispatch({
                    type: SET_CART,
                    payload: cart
                  })
                  setDataResponse(response.data);
                  //create new cart line
                  axios.post(APIs.cartAPI + "/" + response.data.id + "/cartlines", data, { headers })
                    .then(response => {
                      if (response.data) {
                        const cart = JSON.stringify(response.data);
                        dispatch({
                          type: SET_CART,
                          payload: cart
                        })
                        setDataResponse(response.data);
                      }
                    }).catch(error => {
                      alert(error.message);
                    })
                }

              }).catch(error => {
                alert(error.message);
              })
          }
        }).catch(error => {
          alert(error.message);
        })
    }
  };


  return (

    <div>
      <Link to="/">Back to home page</Link>
      <div className="row top">
        <div className="col-2">
          <img
            className="large"
            src={product.photo}
            alt={product.productName}
          ></img>
        </div>
        <div className="col-1">
          <ul>
            <li>
              <h1>{product.productName}</h1>
            </li>
            <li>

              <h2>{product.reviews}</h2>

            </li>
            <li>Pirce : ${product.price}</li>
            <li>
              Description:
                  <p>{product.description}</p>
            </li>
          </ul>
        </div>
        <div className="col-1">
          <div className="card card-body">
            <ul>
              <li>
                Seller{' '}
                {/* <h2>
                      <Link to={`/seller/${product.seller.id}`}>
                        {product.seller.seller.name}
                      </Link>
                    </h2> */}
                {/* <Rating
                      rating={product.seller.seller.rating}
                      numReviews={product.seller.seller.numReviews}
                    ></Rating> */}
              </li>
              <li>
                <div className="row">
                  <div>Price</div>
                  <div className="price">${product.price}</div>
                </div>
              </li>
              <li>
                <div className="row">
                  <div>Status</div>
                  <div>
                    {product.quantityInStock > 0 ? (
                      <span className="success">In Stock</span>
                    ) : (
                      <span className="danger">Unavailable</span>
                    )}
                  </div>
                </div>
              </li>
              {product.quantityInStock > 0 && (
                <>
                  <li>
                    <div className="row">
                      <div>Qty</div>
                      <div>
                        <select
                          value={qty}
                          onChange={(e) => setQty(e.target.value)}
                        >
                          {[...Array(product.quantityInStock).keys()].map(
                            (x) => (
                              <option key={x + 1} value={x + 1}>
                                {x + 1}
                              </option>
                            )
                          )}
                        </select>
                      </div>
                    </div>
                  </li>
                  <li>
                    <button
                      onClick={addToCartHandler}
                      className="primary block"
                    >
                      Add to Cart
                        </button>
                  </li>
                </>
              )}
            </ul>
          </div>
        </div>
      </div>

      <Review productId={productId} />
    </div>
  );
}