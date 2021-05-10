import logo from './logo.svg';
import { BrowserRouter } from 'react-router-dom';
import './App.css';
import Home from "./containers/Home/Home";

function App() {
  return (
      <BrowserRouter>
        <Home/>
      </BrowserRouter>
  );
}

export default App;
