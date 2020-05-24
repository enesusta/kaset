import React, { useState } from 'react'

function Site() {
    const [url, setUrl] = useState("");

    const urlHandler = e => {
        setUrl(e.target.value);
    }

    return (
        <div className="site-container">
            <h5>Insert a valid URL</h5>
            <form className="site-form">
                <input
                    type="text"
                    onChange={urlHandler}
                />
                <button className="button">Download</button>
            </form>
            <h5>{`url is ${url}`}</h5>
        </div>
    )
}

export default Site
