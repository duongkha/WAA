//Main React Entry File
import React from 'react';
import { render } from 'react-dom';
import { Provider } from 'react-redux';

import { store } from './_helpers';
import { App } from './App';
/*The boilerplate application uses a fake / mock backend that stores data 
in browser local storage, to switch to a real backend api simply remove the 
fake backend code below the comment
*/
// setup fake backend
import { configureFakeBackend } from './_helpers';
configureFakeBackend();

render(
    <Provider store={store}>
        <App />
    </Provider>,
    document.getElementById('app')
);