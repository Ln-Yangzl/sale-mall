import {Link} from 'react-router-dom'


export default function SideBar(props) {

    return (
        <div className="left-side sticky-left-side">

            <div className="logo">
                <Link to="/"><img src="images/logo.png" alt="" /></Link>
            </div>



            <div className="left-side-inner">

                <ul className="nav nav-pills nav-stacked custom-nav">

                    <li><Link to="/goodsList"> <span>商品列表</span></Link></li>
                    <li><Link to="/goodsAdd"> <span>添加商品</span></Link></li>
                    
                </ul>

            </div>
        </div>

    );

}