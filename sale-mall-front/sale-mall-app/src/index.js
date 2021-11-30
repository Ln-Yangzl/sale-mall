import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Routes, Route} from 'react-router-dom';

import SalePage from './pages/salePage';

import './base.css'
import './common.css'

function App() {
  return (
    <BrowserRouter>
      <Routes>

        <Route path='/' element={<SalePage/>} />

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
