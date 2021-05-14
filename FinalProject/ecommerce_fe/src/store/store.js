import {GET_USER_INFO, LOGIN_FETCH_SUCCESS, LOGOUT, SET_USER, SET_CART} from "../constants/constants";
import { createStore, compose, applyMiddleware, combineReducers } from 'redux';
import thunk from 'redux-thunk';
import { cartReducer } from '../reducers/cartReducers';


export const INITIAL_STATE = {
  headers: null,
  oAuthToken: localStorage.getItem('oAuthToken'),
  refreshToken: '',
  userInfo: localStorage.getItem('userInfo')? JSON.parse(localStorage.getItem('userInfo')): null,
  cartInfo: localStorage.getItem('cartInfo')? JSON.parse(localStorage.getItem('cartInfo')): null
};


const AuthReducer =(state =[],action) =>{
  switch (action.type) {

    case LOGOUT:
      localStorage.removeItem('userInfo');
      localStorage.removeItem('oAuthToken');
      localStorage.removeItem('cartInfo');
      return INITIAL_STATE;
    case SET_USER:
      let  userInfo  = JSON.parse(action.payload);
      switch (userInfo.roles[0]["id"]){
        case 1:
          userInfo.isSeller = false;
          userInfo.isAdmin =  true;
          userInfo.isBuyer = false;
          break;
        case 2:
          userInfo.isSeller = true;
          userInfo.isAdmin =  false;
          userInfo.isBuyer = false;
          break;
        case 3:
          userInfo.isSeller = false;
          userInfo.isAdmin =  false;
          userInfo.isBuyer = true;
          break;
      }
      const newUserInfo  = JSON.stringify(userInfo);
      localStorage.setItem('userInfo',newUserInfo);
      // console.log("dispatch");
      // console.log(newUserInfo);
      return {...state, newUserInfo }

    case LOGIN_FETCH_SUCCESS:
      const  oAuthToken  = action.payload;
      localStorage.setItem('oAuthToken',oAuthToken);
      return { ...state, headers:{'Access-Control-Allow-Origin': '*',
                                  'Authorization': 'Bearer ' + action.payload},
                          oAuthToken:oAuthToken};
    case SET_CART:
      let cartInfo = action.payload;
      // console.log("dispatch");
      // console.log(localStorage.getItem('cartInfo'));
      // console.log("dispatch");
      // console.log(localStorage.getItem('userInfo'));
      localStorage.setItem('cartInfo',cartInfo);
      return {...state }

    default:
      return state;
  }

}



const store = createStore(AuthReducer,INITIAL_STATE)

export default store;