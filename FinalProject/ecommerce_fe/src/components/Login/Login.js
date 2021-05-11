import React, {useContext, useState} from 'react';
import "./Login.css"
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import axios from 'axios';
import {APIConfig} from "../../store/API-Config";
import {Link} from "react-router-dom";
import {UserInfo} from "../../store/AppContext";

export default function Login(props) {
    const APIs = useContext(APIConfig);
    const { userInfo, setUserInfo } = useContext(UserInfo);
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    function validateForm() {
        return email.length > 0 && password.length > 0;
    }

    function handleSubmit(event) {
        event.preventDefault();

        axios.post(APIs.loginAPI, {
            username:email,
            password:password
        }).then(response => {
           // alert(response.data.token);
            let info = {};
            info.token = response.data.token;
            console.log(info.token);
            const headers = {
                 'Access-Control-Allow-Origin': '*',
                'Authorization': 'Bearer ' + info.token,
            }
            axios(APIs.userAPI + "/current",{headers})
                .then(response=>{
                    info.userdetails = response.data;
                   // alert(response.data);
                }).catch(error => {
                alert(error.message);
            })
            setUserInfo(info);
            props.history.push('/');
        })
            .catch(error => {
                alert(error.message);
            })
    }

    return (
        <div>
            <form className="form" onSubmit={handleSubmit}>
                <div>
                    <h1>Sign In</h1>
                </div>
                {/*{loading && <LoadingBox></LoadingBox>}*/}
                {/*{error && <MessageBox variant="danger">{error}</MessageBox>}*/}
                <div>
                    <label htmlFor="email">Email address</label>
                    <input
                        type="email"
                        id="email"
                        placeholder="Enter email"
                        required
                        onChange={(e) => setEmail(e.target.value)}
                    ></input>
                </div>
                <div>
                    <label htmlFor="password">Password</label>
                    <input
                        type="password"
                        id="password"
                        placeholder="Enter password"
                        required
                        onChange={(e) => setPassword(e.target.value)}
                    ></input>
                </div>
                <div>
                    <label />
                    <button className="primary" type="submit">
                        Sign In
                    </button>
                </div>
                <div>
                    <label />
                    <div>
                        New customer?{' '}
                        <Link to={`/register`}>
                            Create your account
                        </Link>
                    </div>
                </div>
            </form>
        </div>
    );
}