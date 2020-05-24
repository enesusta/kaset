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

    const test = e => {

        const apiCall = `${process.env.REACT_APP_API}/mp3?url=${url}`;

        e.preventDefault();
        fetch(apiCall)
            .then(response => {
                const reader = response.body.getReader();
                console.log(reader);

                reader.read().then(function processText({ done, value }) {
                    // Result objects contain two properties:
                    // done  - true if the stream has already given you all its data.
                    // value - some data. Always undefined when done is true.
                    console.log(value);

                    if (done) {
                        console.log("Stream complete");
                        return;
                    }
                });

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
                <button className="button" onClick={test}>Download</button>
            </form>
        </div>
    )
}

export default Site;
