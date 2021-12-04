import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Routes, Route, Navigate} from 'react-router-dom';

import GoodsList from './pages/goodsListPage';
import GoodsAdd from './pages/goodsAddPage';

import './utils/tools'
import './css/base.css'
import './css/common.css'

function App() {
  return (
    <BrowserRouter>
      <Routes>

        <Route path='/goodsList' element={<GoodsList />} />

        <Route path='/goodsAdd' element={<GoodsAdd />} />

        <Route path='/' element={<Navigate replace to="/goodsList" />}/>
          
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

