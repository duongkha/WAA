import { createStore } from 'redux';
import { cartReducer } from '../reducers/cartReducers';
import {GET_USER_INFO, LOGIN_FETCH_SUCCESS, LOGOUT, SET_USER} from "../constants/constants";


export const INITIAL_STATE = {
  oAuthToken: '',
  refreshToken: '',
  userInfo: localStorage.getItem('userInfo')? localStorage.getItem('userInfo'): null
};


const AuthReducer =(state =[],action) =>{
  switch (action.type) {

    case LOGOUT:
      localStorage.removeItem('userInfo');
      localStorage.removeItem('oAuthToken');
      return INITIAL_STATE;
    case SET_USER:
      const  userInfo  = action.payload;
      localStorage.setItem('userInfo',action.payload);
      return {...state, userInfo }

    case LOGIN_FETCH_SUCCESS:
      const  oAuthToken  = action.payload;
      localStorage.setItem('oAuthToken',oAuthToken);
      return { ...state, oAuthToken };

    default:
      return state;
  }

}
const store = createStore(AuthReducer,INITIAL_STATE)

export default store;