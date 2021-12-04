import React from "react";

/**
 * 后端静态资源地址
 */
React.$staticHeader = 'http://localhost:8081'

/**
 * 后端接口地址
 */
React.$backendHeader = 'http://localhost:8080'

/**
 * 获取后端静态接口url地址
 * @param  url 
 * @returns 
 */
React.$getStaticUrl = (url) => React.$staticHeader + url

/**
 * 获取后端接口url地址
 * @param {*} url 
 * @returns 
 */
React.$getBackendUrl = (url) => React.$backendHeader + url

/**
 * log the error code and error info in the console
 * usually use when the body status code return is not 0
 * @param  responseBody the response body get
 */
 React.$logCommonError = (responseBody) => {
    console.log('error code:' + responseBody.status);
    console.log('error info: ' + responseBody.error);
}

/**
 * log the crucial error in the console
 * usually use when the status is not 2xx or a runtime error
 * @param responseBody he response body get
 */
React.$logRuntimeError = (response) => {
    console.log('runtime error:' + response.status);
}