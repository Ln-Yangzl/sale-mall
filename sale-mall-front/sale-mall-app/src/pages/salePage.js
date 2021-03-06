import React from 'react'
import TopNav from '../componets/topNav';
import GoodsList from '../componets/goodsList';
import Footer from '../componets/footer';

import '../css/salePage.css'

export default function SalePage(props) {

    return (
        <div>
            <TopNav tableId={1}/>
            <div className='main-band'>
                <div className='width-restrictor'>
                    <div className='band-1'>
                        <GoodsList />
                    </div>
                </div>
            </div>
            <Footer />
        </div>
    );
}