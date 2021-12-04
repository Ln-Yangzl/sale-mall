import React from "react";
import axios from "axios"

import SideBar from "../components/sideBar";

export default function GoodsAdd(props) {

    const [product, setProduct] = React.useState({
        title: '',
        disc: '',
        price: '',
        inventory: '',
        startTime: '',
        endTime: '',
        pic: null,
    })

    const [errorMsg, setErrorMsg] = React.useState({
        titleErrorMsg: '',
        discErrorMsg: '',
        priceErrorMsg: '',
        inventoryErrorMsg: '',
        startTimeErrorMsg: '',
        endTimeErrorMsg: '',
        picErrorMsg: null,

    })

    const handleChange = (event, prop) => {
        setProduct({ ...product, [prop]: event.target.value });
    };

    const handleFileChange = () => {
        let file = document.querySelector('#input-pic').files[0];
        console.log(file);
        setProduct({ ...product, pic: file });
    }

    const handleReset = () => {
        setProduct({
            title: '',
            disc: '',
            price: '',
            inventory: '',
            startTime: '',
            endTime: '',
            pic: null,
        })
    }

    const handleCommit = () => {
        let url = React.$getBackendUrl('/product/create');
        let uploadData = {
            product: {
                title: product.title,
                disc: product.disc,
                price: product.price,
                inventory: product.inventory,
                startTime: product.startTime,
                endTime: product.endTime,
            },
            pic: product.pic,
        }
        console.log(uploadData);
        axios.post(url, uploadData).then((response) => {
            let responseBody = response.data;
            if (responseBody.code !== 0) {
                setErrorMsg({
                    ...errorMsg,
                    picErrorMsg: responseBody.message,
                })
            } else {
                React.$logCommonError(responseBody);
            }
        }).catch((response) => {
            React.$logRuntimeError(response)
        })
    }

    return (
        <div className='main-content'>
            <SideBar />
            <div className='content-box'>
                <h2 className='content-header'>添加商品</h2>
                <div className='form-restrictor'>
                    <form className='el-form' action="">
                        <div className='item'>
                            <label className='item-label is-required' for='title'>商品名称：</label>
                            <input className='item-input' type='text' value={product.title} onChange={(event) => handleChange(event, "title")}></input>
                            <div className='error-msg'>{errorMsg.titleErrorMsg}</div>
                        </div>
                        <div className='item'>
                            <label className='item-label is-required' for='disc'>促销标题：</label>
                            <input className='item-input' type='text' value={product.disc} onChange={(event) => handleChange(event, "disc")}></input>
                            <div className='error-msg'>{errorMsg.discErrorMsg}</div>
                        </div>
                        <div className='item'>
                            <label className='item-label is-required' for='price'>商品价格：</label>
                            <input className='item-input' type='text' value={product.price} onChange={(event) => handleChange(event, "price")}></input>
                            <div className='error-msg'>{errorMsg.priceErrorMsg}</div>
                        </div>
                        <div className='item'>
                            <label className='item-label is-required' for='inventory'>商品库存：</label>
                            <input className='item-input' type='text' value={product.inventory} onChange={(event) => handleChange(event, "inventory")}></input>
                            <div className='error-msg'>{errorMsg.inventoryErrorMsg}</div>
                        </div>
                        <div className='item'>
                            <label className='item-label is-required' for='startTime'>开始日期：</label>
                            <input className='item-input' type='text' value={product.startTime} onChange={(event) => handleChange(event, "startTime")}></input>
                            <div className='error-msg'>{errorMsg.startTimeErrorMsg}</div>
                        </div>
                        <div className='item'>
                            <label className='item-label is-required' for='endTime'>结束日期：</label>
                            <input className='item-input' type='text' value={product.endTime} onChange={(event) => handleChange(event, "endTime")}></input>
                            <div className='error-msg'>{errorMsg.endTimeErrorMsg}</div>
                        </div>
                        <div className='item'>
                            <label className='item-label is-required' for='pic'>商品图片：</label>
                            <input type='file' id='input-pic' onChange={() => handleFileChange()}></input>
                            <div className='error-msg'>{errorMsg.picErrorMsg}</div>
                        </div>
                        <div className='form-controller'>
                            <button className='btn btn-danger' type="button" onClick={() => handleReset()}>重置</button>
                            <button className='btn btn-info' type="button" onClick={() => handleCommit()}>提交</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}