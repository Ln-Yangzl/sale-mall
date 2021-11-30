import React from 'react'
import TopNav from '../componets/topNav';
import Footer from '../componets/footer';

import '../css/detailPage.css'

export default function DetailPage(props) {

    return (
        <div>
            <TopNav tableId={0} />
            <div className='main-band'>
                <div className='width-restrictor'>
                    <div className='product-intro'>
                        <div className='preview'>
                            <img src='./upload/goods-img-1.jpg' alt='' />
                        </div>
                        <div className='item-info'>
                            <h2 className='product-title'>法国馥绿德雅（RENE FURTERER）固发白珠洗发露600ml（孙怡推荐 增发密发 强韧发根 洗发水 小白珠）</h2>
                            <ul>
                                <li className='info-row'>
                                    <span className='info-name'>秒杀价</span>
                                    <span className='info-content price'>
                                        <em>￥</em>
                                        318.00
                                    </span>
                                </li>
                                <li className='info-row'>
                                    <span className='info-name'>开始时间</span>
                                    <span className='info-content'>2021-11-30 18:00:00</span>
                                </li>
                                <li className='info-row'>
                                    <span className='info-name'>结束时间</span>
                                    <span className='info-content'>2021-11-30 19:00:00</span>
                                </li>
                            </ul>
                            <div className='purchase'>
                                <div className='purchase-btn btn-red'>立刻购买</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <Footer />
        </div>
    );
}