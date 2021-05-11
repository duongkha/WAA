import React  from 'react';
import Product from '../product/Product';

export default function products() {
    <div className="row center">
        {products.map((product) => (
            <Product key={product._id} product={product}></Product>
        ))}
    </div>
}