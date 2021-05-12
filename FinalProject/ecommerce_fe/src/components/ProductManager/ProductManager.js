import "./ProductManager.css"
import React, {useContext, useEffect, useState} from "react";
import {APIConfig} from "../../store/API-Config";
import store from "../../store/store";
import axios from "axios";

const  ProductManager = ()=>{
    const APIs = useContext(APIConfig);
    const [products,setProducts] = useState([]);
    const state = store.getState();
    const headers = {
        'Access-Control-Allow-Origin': '*',
        'Authorization': 'Bearer ' + state.oAuthToken,
    }
    const deleteHandler = (product) => {
        axios.delete(APIs.productAPI + "/" + product.id , {headers})
            .then(response => {
                if(response.data === true){
                    alert('Deleted');
                }
            }).catch(error => {
            alert(error.message);
        })
    };

    const loadData = ()=>{
        axios(APIs.userAPI + "/mysellerinfo",{headers})
            .then(response =>{
                axios(APIs.sellerAPI + "/" + response.data.id + "/products"  ,{headers})
                    .then(response=>{
                        const info = JSON.stringify(response.data);
                        setProducts(response.data);
                    }).catch(error => {
                    alert(error.message);
                })
            }).catch(error => {
            alert(error.message);
        })

    }
    useEffect(()=>{
        loadData();
    });
    return (
      <div>
          <h1>Product Manager</h1>
          <table className="table">
              <thead>
              <tr>
                  <th>ID</th>
                  <th>NAME</th>
                  <th>PRODUCER</th>
                  <th>COLOR</th>
                  <th>NUM IN STOCK</th>
                  <th>SIZE</th>
                  <th>PRICE</th>
                  <th>ACTIONS</th>
              </tr>
              </thead>
              <tbody>
              {products && products.map((product) => (
                  <tr key={product.id}>
                      <td>{product.id}</td>
                      <td>{product.productName}</td>
                      <td>{product.producer}</td>
                      <td>{product.color}</td>
                      <td>{product.quantityInStock}</td>
                      <td>{product.size}</td>
                      <td>{product.price}</td>
                      <td>
                              <button
                                  type="button"
                                  className="small"
                                  onClick={() =>{}}
                              >
                                  Edit
                              </button>
                          <button
                              type="button"
                              className="small"
                              onClick={() => deleteHandler(product)}
                          >
                              Delete
                          </button>
                      </td>
                  </tr>
              ))}
              </tbody>
          </table>
      </div>
    );
}

export  default  ProductManager;