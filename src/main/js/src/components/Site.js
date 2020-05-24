import React, { useState } from 'react'
import axios from 'axios';

function Site() {
    const [url, setUrl] = useState("");

    const urlHandler = e => {
        setUrl(e.target.value);
    }

    const clickHandler = e => {

        e.preventDefault();
        const apiCall = `${process.env.REACT_APP_API}/mp3?url=${url}`;

        axios({
            url: apiCall,
            method: 'GET',
            responseType: 'blob'
        }).then(res => {
            console.log(`res is ${res.data}`);

        })
            .catch(err => {
                console.log(err);
            });

    }

    return (
        <div className="site-container">
            <p>Insert a valid URL</p>
            <form className="site-form">
                <input
                    type="text"
                    onChange={urlHandler}
                />
                <button className="button" onClick={clickHandler}>Download</button>
            </form>
        </div>
    )
}

export default Site;
