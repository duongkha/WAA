import React, { useContext, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import { addToCart, removeFromCart } from '../../actions/cartActions';
import axios from 'axios';
import { APIConfig } from '../../store/API-Config';


export default function ShoppingCart(props) {
    const productId = props.match.params.id;
    // const APIs = useContext(APIConfig);
    // const productAPI = APIs.productAPI;
    const qty = props.location.search
    ? Number(props.location.search.split('=')[1])
    : 1;
    // const [cartItems, setCartItems] = useState([]);
      const cart = useSelector((state) => state.cart);
      console.log(cart);

      const { cartItems } = cart;
      const dispatch = useDispatch();
      useEffect(() => {
        if (productId) {
          dispatch(addToCart(productId, qty));
        }
      }, [dispatch, productId, qty]);

      const removeFromCartHandler = (id) => {
        dispatch(removeFromCart(id));
      };

    //   const removeFromCartHandler = (id) => {
        
    //   };

    // useEffect(() => {
    //     const fecthData = async () => {
    //         try {
    //             const { data } = await axios.get(productAPI + '/' + productId);
    //             setCartItems(data);

    //             console.log("Success data");
    //           } catch (err) {
    //             console.log(err);
    //           }
    //         };
    //         fecthData();
    //       }, []);
        
    const checkoutHandler = () => {
        props.history.push('/signin?redirect=shipping');
    };
    return (
        <div className="row top">
        <div className="col-2">
            <h1>Shopping Cart</h1>

            {cartItems.length === 0 ? (
            <>
                Cart is empty. <Link to="/">Go Shopping</Link>
            </>
            ) : (
            <ul>
                {cartItems.map((item) => (
                <li key={item.product}>
                    <div className="row">
                    <div>
                        <img
                        src={item.image}
                        alt={item.name}
                        className="small"
                        ></img>
                    </div>
                    <div className="min-30">
                        <Link to={`/product/${item.product}`}>{item.name}</Link>
                    </div>
                    <div>
                        ${qty}
                        {/* <select
                        value={item.qty}
                        onChange={(e) =>
                            dispatch(
                            addToCart(item.product, Number(e.target.value))
                            )
                        }
                        >
                        {[...Array(item.countInStock).keys()].map((x) => (
                            <option key={x + 1} value={x + 1}>
                            {x + 1}
                            </option>
                        ))}
                        </select> */}
                    </div>
                    <div>${item.price}</div>
                    <div>
                        <button
                        type="button"
                        onClick={() => removeFromCartHandler(item.product)}
                        >
                        Delete
                        </button>
                    </div>
                    </div>
                </li>
                ))}
            </ul>
            )}
        </div>
        <div className="col-1">
            <div className="card card-body">
            <ul>
                <li>
                <h2>
                    Subtotal ({cartItems.reduce((a, c) => a + c.qty, 0)} items) : $
                    {cartItems.reduce((a, c) => a + c.price * c.qty, 0)}
                </h2>
                </li>
                <li>
                <button
                    type="button"
                    onClick={checkoutHandler}
                    className="primary block"
                    disabled={cartItems.length === 0}
                >
                    Proceed to Checkout
                </button>
                </li>
            </ul>
            </div>
        </div>
        </div>
    );
}
