import React from 'react'
import { useParams } from "react-router-dom";

import TopNav from '../componets/topNav';
import Footer from '../componets/footer';

import '../css/detailPage.css'
import axios from 'axios';

export default function DetailPage(props) {

    let params = useParams();

    const [product, setProduct] = React.useState({
        seckillId: params.seckillId,
        productId: '',
        title: '',
        disc: '',
        pic: '',
        inventory: '',
        price: '',
        startTime: '',
        endTime: '',
    })

    React.useEffect(() => {
        let url = React.$getBackendUrl('/seckill/getDetail?seckillId=' + params.seckillId);
        axios.get(url).then((response) => {
            let responseBody = response.data;
            if (responseBody.code === 0) {
                setProduct(responseBody.data)
            } else {
                React.$logCommonError(responseBody);
            }
        }).catch((response) => {
            React.$logRuntimeError(response)
        })
    }, [params])

    return (
        <div>
            <TopNav tableId={0} />
            <div className='main-band'>
                <div className='width-restrictor'>
                    <div className='product-intro'>
                        <div className='preview'>
                            <img src={React.$getStaticUrl(product.pic)} alt='' />
                        </div>
                        <div className='item-info'>
                            <h2 className='product-title'>{product.title}</h2>
                            <ul>
                                <li className='info-row'>
                                    <span className='info-name'>秒杀价</span>
                                    <span className='info-content price'>
                                        <em>￥</em>
                                        {product.price}
                                    </span>
                                </li>
                                <li className='info-row'>
                                    <span className='info-name'>开始时间</span>
                                    <span className='info-content'>{product.startTime}</span>
                                </li>
                                <li className='info-row'>
                                    <span className='info-name'>结束时间</span>
                                    <span className='info-content'>{product.endTime}</span>
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