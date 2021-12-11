import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Routes, Route} from 'react-router-dom';

import SalePage from './pages/salePage';
import DetailPage from './pages/detailPage';
import ResultPage from './pages/resultPage';

import './utils/tools'
import './css/base.css'
import './css/common.css'

function App() {
  return (
    <BrowserRouter>
      <Routes>

        <Route path='/' element={<SalePage />} />
        
        <Route path='/detail/:seckillId' element={<DetailPage />} />
        
        <Route path='/result/:serial' element={<ResultPage />} />

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
