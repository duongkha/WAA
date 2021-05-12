import React, {useContext, useEffect, useState} from 'react';
import './Approval.css'
import axios from "axios";
import {SET_USER} from "../../constants/constants";
import {APIConfig} from "../../store/API-Config";
import store from "../../store/store";

const Approval = (props)=>{
    const APIs = useContext(APIConfig);
    //const [sellers,setSellers] = useState({});
    const state = store.getState();
    var  sellers = [];
    const approveHandler = (seller) => {

    };

    const loadData = ()=>{
        const headers = {
            'Access-Control-Allow-Origin': '*',
            'Authorization': 'Bearer ' + state.oAuthToken,
        }
        axios(APIs.sellerAPI,{headers})
            .then(response=>{
                const info = JSON.stringify(response.data);
                sellers = response.data;
            }).catch(error => {
            alert(error.message);
        })
    }
    useEffect(()=>{
        loadData();
    });
return (
   <div>
       <h1>Approve Sellers</h1>
       <table className="table">
           <thead>
           <tr>
               <th>ID</th>
               <th>NAME</th>
               <th>ACTIONS</th>
           </tr>
           </thead>
           <tbody>
           {sellers && sellers.map((seller) => (
               <tr key={seller.id}>
                   <td>{seller.id}</td>
                   <td>{seller.companyName}</td>
                   <td>
                       <button
                           type="button"
                           className="small"
                           onClick={() =>
                               props.history.push(`/product/${seller.id}/edit`)
                           }
                       >
                           Edit
                       </button>
                       <button
                           type="button"
                           className="small"
                           onClick={() => approveHandler(seller)}
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

export  default Approval;