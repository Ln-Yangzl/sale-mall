import React from 'react';
import axios from 'axios';
import { useParams } from "react-router-dom";

import TopNav from '../componets/topNav';
import Footer from '../componets/footer';

import '../css/resultPage.css'


export default function ResultPage(props) {

    let params = useParams();

    const sleep = (ms) => {
        return new Promise((resolve) => setTimeout(resolve, ms));
    }

    const [order, setOrder] = React.useState({
        orderId: '',
        orderState: '',
    })

    React.useEffect(() => {
        setOrder({
            orderId: '',
            orderState: '查询中...'
        });
        sleep(3000).then(() => {
            let url = React.$getBackendUrl('/order/getBySerial?serial=' + params.serial);
            axios.get(url).then((response) => {
                let responseBody = response.data;
                if (responseBody.code === 0) {
                    setOrder({
                        orderId: responseBody.data,
                        orderState: '成功'
                    })
                } else if (responseBody.code === 4) {
                    setOrder({
                        orderState: '系统繁忙，请稍后刷新页面重试'
                    });
                    React.$logCommonError(responseBody);
                } else {
                    React.$logCommonError(responseBody);
                }
            }).catch((response) => {
                React.$logRuntimeError(response);
            })
        })
    }, [params])

    return (
        <div>
            <TopNav tableId={0} />
            <div className='main-band'>
                <div className='width-restrictor'>
                    <div className='result-box'>
                        <h2>{'订单流水号:' + params.serial}</h2>
                        <p>{order.orderState}</p>
                    </div>
                </div>
            </div>
            <Footer />
        </div>
    );
}