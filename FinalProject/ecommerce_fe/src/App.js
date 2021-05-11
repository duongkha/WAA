import logo from './logo.svg';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { BrowserRouter as Router, Switch, Route, Link,Redirect } from "react-router-dom";
import Login from "./components/Login/Login";
import SignUp from "./components/SignUp/SignUp";
import {APIConfig} from "./store/API-Config";
import React from "react";
import Home from "./containers/Home/Home";


function App() {
  return (
      <APIConfig.Provider value={
        {
          registerAPI: 'http://localhost:8080/register',
          loginAPI: 'http://localhost:8080/authenticate',
          logoutAPI: 'http://localhost:8080/logout',
          productAPI: 'http://localhost:8080/products',
          orderAPI: 'http://localhost:8080/orders',
          sellerAPI: 'http://localhost:8080/sellers',
          categoryAPI: 'http://localhost:8080/categories',
          roleAPI: 'http://localhost:8080/roles',
          userAPI: 'http://localhost:8080/user',
        }
      }>
        <Router>
          <div className="App">
            <nav className="navbar navbar-expand-lg navbar-light fixed-top">
              <div className="container">
                <div className="collapse navbar-collapse" id="navbarTogglerDemo02">
                  <ul className="navbar-nav ml-auto">
                    <li className="nav-item">
                      <Link className="nav-link" to={"/sign-in"}>Login</Link>
                    </li>
                    <li className="nav-item">
                      <Link className="nav-link" to={"/sign-up"}>Sign up</Link>
                    </li>
                  </ul>
                </div>
              </div>
            </nav>

            <div className="auth-wrapper">
              <div className="auth-inner">
                <Switch>
                  <Route exact path='/' component={Home} />
                  <Route path="/sign-in" component={Login} />
                  <Route path="/sign-up" component={SignUp} />
                  <Redirect from="/" to="/home" />
                </Switch>
              </div>
            </div>
          </div>
        </Router>
      </APIConfig.Provider>
  );
}

export default App;
