import React, { useEffect } from 'react';
import "./Home.css"
import { APIConfig } from '../../store/API-Config';

const Home =(props)=>{
    return (
        <APIConfig.Provider value={
            {
                registerAPI: 'http://localhost:8080/register',
                loginAPI: 'http://localhost:8080/authenticate',
                logoutAPI: 'http://localhost:8080/logout',
                productAPI: 'http://localhost:8080/products',
                orderAPI: 'http://localhost:8080/orders',
            }
        }>
        <div>
            <h1>Home Page</h1>
        </div>
        </APIConfig.Provider>
    );
}

export default Home;