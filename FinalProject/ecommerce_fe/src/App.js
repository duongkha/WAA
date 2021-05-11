import logo from './logo.svg';
import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import './App.css';
import {BrowserRouter as Router, Switch, Route, Link, Redirect, BrowserRouter} from "react-router-dom";
import Login from "./components/Login/Login";
import SignUp from "./components/SignUp/SignUp";
//import {APIConfig} from "./store/API-Config";
import { signout } from './actions/userActions';
import Home from "./containers/Home/Home";



function App() {
  const [sidebarIsOpen, setSidebarIsOpen] = useState(false);
  // const userSignin = useSelector((state) => state.userSignin);
  // const { userInfo } = userSignin;

  // const dispatch = useDispatch();
  // const signoutHandler = () => {
  //   dispatch(signout());
  // };

  // const productCategoryList = useSelector((state) => state.productCategoryList);
  // const {
  //   loading: loadingCategories,
  //   error: errorCategories,
  //   categories,
  // } = productCategoryList;

  return (
      <BrowserRouter>
        <div className="grid-container">
          <header className="row">
            <div>
              <button
                  type="button"
                  className="open-sidebar"
                  onClick={() => setSidebarIsOpen(true)}
              >
                <i className="fa fa-bars"></i>
              </button>
              <Link className="brand" to="/">
                WAA
              </Link>
            </div>

            <div>
              <Link to="/cart">
                Cart
                {/*{cartItems.length > 0 && (*/}
                {/*    <span className="badge">{cartItems.length}</span>*/}
                {/*)}*/}
              </Link>

              {/*{userInfo ? (*/}
              {/*    <div className="dropdown">*/}
              {/*      <Link to="#">*/}
              {/*        {userInfo.name} <i className="fa fa-caret-down"></i>{' '}*/}
              {/*      </Link>*/}
              {/*      <ul className="dropdown-content">*/}
              {/*        <li>*/}
              {/*          <Link to="/profile">User Profile</Link>*/}
              {/*        </li>*/}
              {/*        <li>*/}
              {/*          <Link to="/orderhistory">Order History</Link>*/}
              {/*        </li>*/}
              {/*        <li>*/}
              {/*          <Link to="#signout" onClick={signoutHandler}>*/}
              {/*            Sign Out*/}
              {/*          </Link>*/}
              {/*        </li>*/}
              {/*      </ul>*/}
              {/*    </div>*/}
              {/*) : (*/}
              {/*    <Link to="/signin">Sign In</Link>*/}
              {/*)}*/}
              {/*{userInfo && userInfo.isSeller && (*/}
              {/*    <div className="dropdown">*/}
              {/*      <Link to="#admin">*/}
              {/*        Seller <i className="fa fa-caret-down"></i>*/}
              {/*      </Link>*/}
              {/*      <ul className="dropdown-content">*/}
              {/*        <li>*/}
              {/*          <Link to="/productlist/seller">Products</Link>*/}
              {/*        </li>*/}
              {/*        <li>*/}
              {/*          <Link to="/orderlist/seller">Orders</Link>*/}
              {/*        </li>*/}
              {/*      </ul>*/}
              {/*    </div>*/}
              {/*)}*/}
              {/*{userInfo && userInfo.isAdmin && (*/}
              {/*    <div className="dropdown">*/}
              {/*      <Link to="#admin">*/}
              {/*        Admin <i className="fa fa-caret-down"></i>*/}
              {/*      </Link>*/}
              {/*      <ul className="dropdown-content">*/}
              {/*        <li>*/}
              {/*          <Link to="/dashboard">Dashboard</Link>*/}
              {/*        </li>*/}
              {/*        <li>*/}
              {/*          <Link to="/productlist">Products</Link>*/}
              {/*        </li>*/}
              {/*        <li>*/}
              {/*          <Link to="/orderlist">Orders</Link>*/}
              {/*        </li>*/}
              {/*        <li>*/}
              {/*          <Link to="/userlist">Users</Link>*/}
              {/*        </li>*/}
              {/*        <li>*/}
              {/*          <Link to="/support">Support</Link>*/}
              {/*        </li>*/}
              {/*      </ul>*/}
              {/*    </div>*/}
              {/*)}*/}
            </div>
          </header>
          <aside className={sidebarIsOpen ? 'open' : ''}>
            <ul className="categories">
              <li>
                <strong>Categories</strong>
                <button
                    onClick={() => setSidebarIsOpen(false)}
                    className="close-sidebar"
                    type="button"
                >
                  <i className="fa fa-close"></i>
                </button>
              </li>
              {/*{(*/}
              {/*    categories.map((c) => (*/}
              {/*        <li key={c}>*/}
              {/*          <Link*/}
              {/*              to={`/search/category/${c}`}*/}
              {/*              onClick={() => setSidebarIsOpen(false)}*/}
              {/*          >*/}
              {/*            {c}*/}
              {/*          </Link>*/}
              {/*        </li>*/}
              {/*    ))*/}
              {/*)}*/}
            </ul>
          </aside>
          <main>
            {/*<Route path="/seller/:id" component={SellerScreen}></Route>*/}
            {/*<Route path="/cart/:id?" component={CartScreen}></Route>*/}
            {/*<Route path="/product/:id" component={ProductScreen} exact></Route>*/}
            {/*<Route*/}
            {/*    path="/product/:id/edit"*/}
            {/*    component={ProductEditScreen}*/}
            {/*    exact*/}
            {/*></Route>*/}
            {/*<Route path="/login" component={Login}></Route>*/}
            <Route path="/register" component={SignUp}></Route>
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
  );
}

export default App;