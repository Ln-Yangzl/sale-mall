import {Link} from 'react-router-dom'


export default function SideBar(props) {

    return (
        <div className="left-side sticky-left-side">

            <div className="logo">
                <Link to="/"><img src="images/logo.png" alt="" /></Link>
            </div>



            <div className="left-side-inner">

                <ul className="nav nav-pills nav-stacked custom-nav">

                    <li><Link to="/"> <span>商品列表</span></Link></li>
                    <li><Link to="/"> <span>发件箱</span></Link></li>
                    <li><Link to="/"> <span>草稿箱</span></Link></li>
                    <li><Link to="/"> <span>通讯录</span></Link></li>

                </ul>

            </div>
        </div>

    );

}