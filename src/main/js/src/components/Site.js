import React, { useState, useEffect } from 'react'
import axios from 'axios';


function Site() {
    const [url, setUrl] = useState("");
    const [isDone, setIsDone] = useState(false);
    const [percent, setPercent] = useState(0);

    const urlHandler = e => {
        setUrl(e.target.value);
    }

    const clickHandler = e => {

        e.preventDefault();

        console.log("clicked");
        const apiCall = `${process.env.REACT_APP_API}/mp3?url=${url}`;

        setIsDone(false);

        axios
            .get(apiCall)
            .then(res => {
                console.log(res.data);
                setIsDone(true);
            });

    };

    return (
        <div className="site-container">
            <p>Insert a valid URL</p>
            <form className="site-form">
                <input
                    type="text"
                    onChange={urlHandler}
                />
                <button className="button" onClick={clickHandler}>Download</button>
                {!isDone ? "in-progress" : "done"}
            </form>
        </div>
    )
}

export default Site;

/**
 *     return new Promise((resolve, reject) => {

            const oReq = new XMLHttpRequest();
            oReq.overrideMimeType("application/octet-stream");
            oReq.responseType("arraybuffer");
            oReq.open('GET', apiCall, true);
            oReq.send();
        });

 */