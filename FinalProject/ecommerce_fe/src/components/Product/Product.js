import React from 'react';
import { Link } from 'react-router-dom';


export default function Product(props) {
  const { product } = props;
  return (
    <div key={product._id} className="card">
      <Link to={`/product/${product.id}`}>
        <img className="medium" src={product.photo} alt={product.productName} />
      </Link>
      <div className="card-body">
        <Link to={`/product/${product.id}`}>
          <h2>{product.name}</h2>
        </Link>
        <div className="row">
          <div className="price">${product.price}</div>
          <div>
            <Link to={`/seller/${product.producter}`}>
              {product.producter}
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}