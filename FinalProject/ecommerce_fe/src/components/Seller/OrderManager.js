import "./OrderManager.css"
import React, {useContext, useEffect, useState} from "react";
import {APIConfig} from "../../store/API-Config";
import store from "../../store/store";
import axios from "axios";

const OrderManager = ()=>{
    const APIs = useContext(APIConfig);
    const [orders,setOrders] = useState([]);
    const state = store.getState();
    const headers = {
        'Access-Control-Allow-Origin': '*',
        'Authorization': 'Bearer ' + state.oAuthToken,
    }
    const cancelHandler = (order) => {
        axios(APIs.sellerAPI + "/" + order.id+ "/cancel", {headers})
            .then(response => {
                if(response.data === true){
                    order.currentStatus = "CANCELLED";
                    loadData();
                }
            }).catch(error => {
            alert(error.message);
        })
    };
    const shippedHandler = (order) => {
        axios(APIs.sellerAPI + "/" + order.id+ "/shipped", {headers})
            .then(response => {
                if(response.data === true){
                    loadData();
                }
            }).catch(error => {
            alert(error.message);
        })
    };
    const deliveredHandler = (order) => {
        axios(APIs.sellerAPI + "/" + order.id+ "/delivered", {headers})
            .then(response => {
                if(response.data === true){
                    loadData();
                }
            }).catch(error => {
            alert(error.message);
        })
    };

    const loadData = ()=>{
        axios(APIs.userAPI + "/mysellerinfo",{headers})
            .then(response =>{
                axios(APIs.sellerAPI + "/" + response.data.id + "/orders"  ,{headers})
                    .then(response=>{
                        const info = JSON.stringify(response.data);
                        setOrders(response.data);
                    }).catch(error => {
                    alert(error.message);
                })
            }).catch(error => {
            alert(error.message);
        })

    }
    useEffect(()=>{
        loadData();
    },[]);
    return (
      <div>
          <h1>Order Manager</h1>
          <table className="table">
              <thead>
              <tr>
                  <th>ID</th>
                  <th>DATE</th>
                  <th>TOTAL</th>
                  <th>BUYER</th>
                  <th>STATUS</th>
                  <th>ACTIONS</th>
              </tr>
              </thead>
              <tbody>
              {orders && orders.map((order) => (
                  <tr key={order.id}>
                      <td>{order.id}</td>
                      <td>{order.orderDate}</td>
                      <td>{order.totalMoney}</td>
                      <td>{order.buyer.user.firstName} { order.buyer.user.lastName}</td>
                      <td>{order.currentStatus}</td>
                      <td>

                              <button
                                  type="button"
                                  className="small"
                                  onClick={() => cancelHandler(order)}
                              >
                                  Cancel
                              </button>
                          <button
                              type="button"
                              className="small"
                              onClick={() => shippedHandler(order)}
                          >
                              Shipped
                          </button>
                          <button
                              type="button"
                              className="small"
                              onClick={() => deliveredHandler(order)}
                          >
                              Delivered
                          </button>
                      </td>
                  </tr>
              ))}
              </tbody>
          </table>
      </div>
    );
}
export  default OrderManager;