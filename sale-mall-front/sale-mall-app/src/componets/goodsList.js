import axios from 'axios'
import React from 'react';
import { Link } from "react-router-dom";

export default function GoodsList(props) {

    // let data = [
    //     {
    //         title: '潘婷氨基酸乳液修洗护套装',
    //         disc: '限量抢购300件',
    //         price: '9.9',
    //         pic: './upload/goods-img-1.jpg'
    //     },
    //     {
    //         title: '潘婷氨基酸乳液修洗护套装',
    //         disc: '限量抢购300件',
    //         price: '9.9',
    //         pic: './upload/goods-img-1.jpg'
    //     },
    //     {
    //         title: '潘婷氨基酸乳液修洗护套装',
    //         disc: '限量抢购300件',
    //         price: '9.9',
    //         pic: './upload/goods-img-1.jpg'
    //     },
    //     {
    //         title: '潘婷氨基酸乳液修洗护套装',
    //         disc: '限量抢购300件',
    //         price: '9.9',
    //         pic: './upload/goods-img-1.jpg'
    //     },
    //     {
    //         title: '潘婷氨基酸乳液修洗护套装',
    //         disc: '限量抢购300件',
    //         price: '9.9',
    //         pic: './upload/goods-img-1.jpg'
    //     },
    //     {
    //         title: '潘婷氨基酸乳液修洗护套装',
    //         disc: '限量抢购300件',
    //         price: '9.9',
    //         pic: './upload/goods-img-1.jpg'
    //     },
    //     {
    //         title: '潘婷氨基酸乳液修洗护套装',
    //         disc: '限量抢购300件',
    //         price: '9.9',
    //         pic: './upload/goods-img-1.jpg'
    //     },
    // ]

    const [data, setData] = React.useState([]);

    React.useEffect(() => {
        let url = React.$getBackendUrl('/seckill/getAll');
        axios.get(url).then((response) => {
            let responseBody = response.data;
            if (responseBody.code === 0) {
                setData(responseBody.data)
            } else {
                React.$logCommonError(responseBody);
            }
        }).catch((response) => {
            React.$logRuntimeError(response)
        })
    }, [])

    return (
        <ul className='goods-list'>
            {
                data.map((value, index) => {
                    return (
                        <li className='goods-item' key={index}>
                            <div className='goods-main'>
                                <img src={React.$getStaticUrl(value.pic)} alt=''></img>
                                <h4 className='goods-title'>{value.title}</h4>
                                <p className='goods-disc'>{value.disc}</p>
                            </div>
                            <div className='goods-info'>
                                <div className='price'>
                                    <em>￥</em>
                                    {value.price}
                                </div>
                                <Link to={'/detail/' + value.seckillId} target = "_blank">
                                    <div className='buy-btn'>
                                        立即抢购
                                    </div>
                                </Link>
                            </div>
                        </li>
                    );
                })
            }

        </ul>
    );
}