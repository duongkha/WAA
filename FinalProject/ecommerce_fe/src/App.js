import logo from './logo.svg';
import React, { useEffect, useState,useContext } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import './App.css';
import {BrowserRouter as Router, Switch, Route, Link, Redirect, BrowserRouter} from "react-router-dom";
import Login from "./components/Login/Login";
import SignUp from "./components/SignUp/SignUp";
import {APIConfig} from "./store/API-Config";
import Home from "./containers/Home/Home";
import {UserInfo} from "./store/AppContext";
import store from "./store/store";
import {LOGOUT, SET_USER} from "./constants/constants";
import Approval from "./components/Approval/Approval";
import Products from './containers/Products/Products';



function App() {
  const [sidebarIsOpen, setSidebarIsOpen] = useState(false);
  const [userInfo, setUserInfo ] = useState(null);


  const state = store.getState();
  const dispatch = useDispatch();

  const signoutHandler = () => {
    dispatch({
      type: LOGOUT
    });
    document.location.href = '/';
  };

  useEffect(()=>{
    setUserInfo(state.userInfo);
  });

  // const productCategoryList = useSelector((state) => state.productCategoryList);
  // const {
  //   loading: loadingCategories,
  //   error: errorCategories,
  //   categories,
  // } = productCategoryList;

  return (
      <APIConfig.Provider value={
        {
          registerAPI: 'http://localhost:8080/api/register',
          loginAPI: 'http://localhost:8080/authenticate',
          logoutAPI: 'http://localhost:8080/logout',
          productAPI: 'http://localhost:8080/api/products',
          orderAPI: 'http://localhost:8080/api/orders',
          sellerAPI: 'http://localhost:8080/api/sellers',
          categoryAPI: 'http://localhost:8080/api/categories',
          roleAPI: 'http://localhost:8080/api/roles',
          userAPI: 'http://localhost:8080/api/users',
          adminAPI: 'http://localhost:8080/api/admin',
        }
      }>
        <UserInfo.Provider value={{ userInfo, setUserInfo }}>
          <BrowserRouter>
            <div className="grid-container">
              <header className="row">
                <div>

                  <Link className="brand" to="/">
                    Online Market
                  </Link>
                </div>
                  <div>
                    {userInfo && userInfo.isBuyer && (
                  <Link to="/cart">
                    Cart
                    {/*{cartItems.length > 0 && (*/}
                    {/*    <span className="badge">{cartItems.length}</span>*/}
                    {/*)}*/}
                  </Link>
                  )}

                  {userInfo && userInfo.isSeller && (
                      <div className="dropdown">
                        <Link to="#admin">
                          Seller <i className="fa fa-caret-down"></i>
                        </Link>
                        <ul className="dropdown-content">
                          <li>
                            <Link to="/productlist/seller">Products</Link>
                          </li>
                          <li>
                            <Link to="/orderlist/seller">Orders</Link>
                          </li>
                        </ul>
                      </div>
                  )}
                  {userInfo && userInfo.isAdmin && (
                      <div className="dropdown">
                        <Link to="#admin">
                          Admin <i className="fa fa-caret-down"></i>
                        </Link>
                        <ul className="dropdown-content">
                          <li>
                            <Link to="/dashboard">Dashboard</Link>
                          </li>
                          <li>
                            <Link to="/productlist">Products</Link>
                          </li>
                          <li>
                            <Link to="/orderlist">Orders</Link>
                          </li>
                          <li>
                            <Link to="/approval">New Sellers</Link>
                          </li>
                        </ul>
                      </div>
                  )}
                    {userInfo ? (
                        <div className="dropdown">
                          <Link to="#">
                            {userInfo.username} <i className="fa fa-caret-down"></i>{' '}
                          </Link>
                          <ul className="dropdown-content">
                            <li>
                              <Link to="/profile">User Profile</Link>
                            </li>
                            {userInfo.isBuyer && (
                            <li>
                              <Link to="/orderhistory">Order History</Link>
                            </li>
                            )}
                            <li>
                              <Link to="#signout" onClick={signoutHandler}>
                                Sign Out
                              </Link>
                            </li>
                          </ul>
                        </div>
                    ) : (
                        <Link to="/signin">Sign In</Link>
                    )}
                </div>
              </header>

              <main>
                {/*<Route path="/seller/:id" component={SellerScreen}></Route>*/}
                {/*<Route path="/cart/:id?" component={CartScreen}></Route>*/}
                {/*<Route path="/product/:id" component={ProductScreen} exact></Route>*/}
                {/*<Route*/}
                {/*    path="/product/:id/edit"*/}
                {/*    component={ProductEditScreen}*/}
                {/*    exact*/}
                {/*></Route>*/}
                <Route path="/approval" component={Approval}></Route>
                <Route path="/signin" component={Login}></Route>
                <Route path="/register" component={SignUp}></Route>
                <Route path="/product/:id" component={Products} exact></Route>
                {/*<Route path="/shipping" component={ShippingAddressScreen}></Route>*/}
                {/*<Route path="/payment" component={PaymentMethodScreen}></Route>*/}
                {/*<Route path="/placeorder" component={PlaceOrderScreen}></Route>*/}
                {/*<Route path="/order/:id" component={OrderScreen}></Route>*/}
                {/*<Route path="/orderhistory" component={OrderHistoryScreen}></Route>*/}
                {/*<Route*/}
                {/*    path="/search/name/:name?"*/}
                {/*    component={SearchScreen}*/}
                {/*    exact*/}
                {/*></Route>*/}
                {/*<Route*/}
                {/*    path="/search/category/:category"*/}
                {/*    component={SearchScreen}*/}
                {/*    exact*/}
                {/*></Route>*/}
                {/*<Route*/}
                {/*    path="/search/category/:category/name/:name"*/}
                {/*    component={SearchScreen}*/}
                {/*    exact*/}
                {/*></Route>*/}
                {/*<Route*/}
                {/*    path="/search/category/:category/name/:name/min/:min/max/:max/rating/:rating/order/:order/pageNumber/:pageNumber"*/}
                {/*    component={SearchScreen}*/}
                {/*    exact*/}
                {/*></Route>*/}
                {/*<PrivateRoute*/}
                {/*    path="/profile"*/}
                {/*    component={ProfileScreen}*/}
                {/*></PrivateRoute>*/}
                {/*<PrivateRoute path="/map" component={MapScreen}></PrivateRoute>*/}
                {/*<AdminRoute*/}
                {/*    path="/productlist"*/}
                {/*    component={ProductListScreen}*/}
                {/*    exact*/}
                {/*></AdminRoute>*/}
                {/*<AdminRoute*/}
                {/*    path="/productlist/pageNumber/:pageNumber"*/}
                {/*    component={ProductListScreen}*/}
                {/*    exact*/}
                {/*></AdminRoute>*/}
                {/*<AdminRoute*/}
                {/*    path="/orderlist"*/}
                {/*    component={OrderListScreen}*/}
                {/*    exact*/}
                {/*></AdminRoute>*/}
                {/*<AdminRoute path="/userlist" component={UserListScreen}></AdminRoute>*/}
                {/*<AdminRoute*/}
                {/*    path="/user/:id/edit"*/}
                {/*    component={UserEditScreen}*/}
                {/*></AdminRoute>*/}

                {/*<AdminRoute*/}
                {/*    path="/dashboard"*/}
                {/*    component={DashboardScreen}*/}
                {/*></AdminRoute>*/}
                {/*<AdminRoute path="/support" component={SupportScreen}></AdminRoute>*/}

                {/*<SellerRoute*/}
                {/*    path="/productlist/seller"*/}
                {/*    component={ProductListScreen}*/}
                {/*></SellerRoute>*/}
                {/*<SellerRoute*/}
                {/*    path="/orderlist/seller"*/}
                {/*    component={OrderListScreen}*/}
                {/*></SellerRoute>*/}

                <Route path="/" component={Home} exact></Route>
              </main>
              <footer className="row center">
                <div>All right reserved</div>{' '}
              </footer>
            </div>
          </BrowserRouter>
        </UserInfo.Provider>
      </APIConfig.Provider>
  );
}

export default App;
