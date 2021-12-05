import axios from 'axios'
import React from 'react';

import SideBar from "../components/sideBar";

export default function GoodsList(props) {

    // let data = [
    //     {
    //         productId: '1',
    //         pic: './upload/goods-img-1.jpg',
    //         title: '潘婷氨基酸乳液修洗护套装',
    //         inventory: '100'
    //     },
    //     {
    //         productId: '1',
    //         pic: './upload/goods-img-1.jpg',
    //         title: '潘婷氨基酸乳液修洗护套装',
    //         inventory: '100'
    //     },
    //     {
    //         productId: '1',
    //         pic: './upload/goods-img-1.jpg',
    //         title: '潘婷氨基酸乳液修洗护套装',
    //         inventory: '100'
    //     },
    //     {
    //         productId: '1',
    //         pic: './upload/goods-img-1.jpg',
    //         title: '潘婷氨基酸乳液修洗护套装',
    //         inventory: '100'
    //     },
    //     {
    //         productId: '1',
    //         pic: './upload/goods-img-1.jpg',
    //         title: '潘婷氨基酸乳液修洗护套装',
    //         inventory: '100'
    //     },
    //     {
    //         productId: '1',
    //         pic: './upload/goods-img-1.jpg',
    //         title: '潘婷氨基酸乳液修洗护套装',
    //         inventory: '100'
    //     },
    //     {
    //         productId: '1',
    //         pic: './upload/goods-img-1.jpg',
    //         title: '潘婷氨基酸乳液修洗护套装',
    //         inventory: '100'
    //     },
    //     {
    //         productId: '1',
    //         pic: './upload/goods-img-1.jpg',
    //         title: '潘婷氨基酸乳液修洗护套装',
    //         inventory: '100'
    //     },
    //     {
    //         productId: '1',
    //         pic: './upload/goods-img-1.jpg',
    //         title: '潘婷氨基酸乳液修洗护套装',
    //         inventory: '100'
    //     },
    // ]

    const [data, setData] = React.useState([])

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
        <div className='main-content'>
            <SideBar />
            <div className='content-box'>
                <h2 className='content-header'>商品列表</h2>
                <div className='table-restrictor'>
                    <table className='table'>
                        <thead>
                            <tr>
                                <th>编号</th>
                                <th>图片</th>
                                <th>商品名称</th>
                                <th>价格</th>
                                <th>库存</th>
                                <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                            {data.map((value, index) => {
                                return (
                                    <tr key={index}>
                                        <th>{value.productId}</th>
                                        <th><img src={React.$getStaticUrl(value.pic)} alt='' /></th>
                                        <th>{value.title}</th>
                                        <th>{value.price}</th>
                                        <th>{value.inventory}</th>
                                        <th>
                                            <button className='btn btn-danger'>删除</button>
                                            <button className='btn btn-default'>查看</button>
                                        </th>
                                    </tr>
                                );
                            })}
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
}