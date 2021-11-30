import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Routes, Route} from 'react-router-dom';

import SalePage from './pages/salePage';
import DetailPage from './pages/detailPage';

import './css/base.css'
import './css/common.css'

function App() {
  return (
    <BrowserRouter>
      <Routes>

        <Route path='/' element={<SalePage />} />
        
        <Route path='/detail' element={<DetailPage/>} />

      </Routes>
    </BrowserRouter>
  )
}

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);
