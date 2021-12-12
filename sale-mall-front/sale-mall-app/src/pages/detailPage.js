import React from 'react';
import moment from 'moment'
import { useParams, useNavigate } from "react-router-dom";
import { nanoid } from 'nanoid';

import TopNav from '../componets/topNav';
import Footer from '../componets/footer';

import '../css/detailPage.css'
import axios from 'axios';

export default function DetailPage(props) {

    let params = useParams();
    let navigate = useNavigate();

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

    const [isAccessible, setAccessible] = React.useState(false);
    const [isOver, setOver] = React.useState(false);

    React.useEffect(() => {
        let url = React.$getBackendUrl('/seckill/getDetail?seckillId=' + params.seckillId);
        axios.get(url).then((response) => {
            let responseBody = response.data;
            if (responseBody.code === 0) {
                // responseBody.data.startTime = "2021-12-11 15:40:10"
                responseBody.data.startTime = moment(responseBody.data.startTime).format('YYYY-MM-DD HH:mm:ss');
                responseBody.data.endTime = moment(responseBody.data.endTime).format('YYYY-MM-DD HH:mm:ss');
                setProduct(responseBody.data)
                let timeCount = getTimeDiff(getCurrentTime(), responseBody.data.startTime)
                if (timeCount < 0){
                    setAccessible(true);
                } else {
                    let interval = setInterval(() => {
                        timeCount--;
                        // console.log(timeCount);
                        if (timeCount === 0) {
                            clearInterval(interval);       
                            setAccessible(true);
                        }
                    }, 1000);
                }
                if (getTimeDiff(getCurrentTime(), responseBody.data.endTime) < 0) {
                    setAccessible(false);
                    setOver(true);
                }
            } else {
                React.$logCommonError(responseBody);
            }
        }).catch((response) => {
            React.$logRuntimeError(response)
        })
    }, [params])

    const getCurrentTime = () => {
        return moment().format('YYYY-MM-DD HH:mm:ss');
    }

    const getTimeDiff = (oldTime, newTime) => {
        return moment(newTime).diff(oldTime, 'second');
    }

    const handleClickBuy = () => {
        if (isAccessible === false) {
            return;
        }
        let serial = nanoid(16);
        let url = React.$getBackendUrl('/create/seckill/' + params.seckillId);
        let payload = new FormData();
        payload.append('userId', '1');
        payload.append('amount', '1');
        payload.append('serial', serial);
        axios.post(url, payload).then((response) => {
            let responseBody = response.data;
            if (responseBody.code === 0) {
                console.log("success!");
                navigate("/result/" + serial);
            } else {
                React.$logCommonError(responseBody);
            }
        }).catch((response) => {
            React.$logRuntimeError(response);
        })
    }

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
                                {   isAccessible ?
                                    <div className='purchase-btn btn-red' onClick={() => handleClickBuy()}>立刻购买</div> :
                                    <div className='wait-btn btn-black'>{isOver ? '已结束' : '等待开始'}</div>
                                }
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <Footer />
        </div>
    );
}