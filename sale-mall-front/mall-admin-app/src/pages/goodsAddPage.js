

import SideBar from "../components/sideBar";

export default function GoodsAdd(props) {
    
    return (
        <div className='main-content'>
            <SideBar />
            <div className='content-box'>
                <h2 className='content-header'>添加商品</h2>
                <div className='form-restrictor'>
                    <form className='el-form'>
                        <div className='item'>
                            <label className='item-label is-required' for='title'>商品名称：</label>
                            <input className='item-input' type='text'></input>
                            <div className='error-msg'></div>
                        </div>
                        <div className='item'>
                            <label className='item-label is-required' for='disc'>促销标题：</label>
                            <input className='item-input' type='text'></input>
                            <div className='error-msg'></div>
                        </div>
                        <div className='item'>
                            <label className='item-label is-required' for='price'>商品价格：</label>
                            <input className='item-input' type='text'></input>
                            <div className='error-msg'></div>
                        </div>
                        <div className='item'>
                            <label className='item-label is-required' for='inventory'>商品库存：</label>
                            <input className='item-input' type='text'></input>
                            <div className='error-msg'></div>
                        </div>
                        <div className='item'>
                            <label className='item-label is-required' for='startTime'>开始日期：</label>
                            <input className='item-input' type='text'></input>
                            <div className='error-msg'></div>
                        </div>
                        <div className='item'>
                            <label className='item-label is-required' for='endTime'>结束日期：</label>
                            <input className='item-input' type='text'></input>
                            <div className='error-msg'></div>
                        </div>
                        <div className='item'>
                            <label className='item-label is-required' for='pic'>商品图片：</label>
                            <input type='file'></input>
                            <div className='error-msg'></div>
                        </div>
                        <div className='form-controller'>
                            <button className='btn btn-danger'>重置</button>
                            <button className='btn btn-info'>提交</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
}