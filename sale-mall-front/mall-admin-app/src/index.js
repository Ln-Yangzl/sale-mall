import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Routes, Route} from 'react-router-dom';

import GoodsList from './pages/goodsListPage';

import './css/base.css'
import './css/common.css'

function App() {
  return (
    <BrowserRouter>
      <Routes>

        <Route path='/' element={<GoodsList />} />

      </Routes>
    </BrowserRouter>
  );
}

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);

