import React, { useState } from 'react'

function Site() {
    const [url, setUrl] = useState("");

    const urlHandler = e => {
        setUrl(e.target.value);
    }

    return (
        <div className="site-container">
            <p>Insert a valid URL</p>
            <form className="site-form">
                <input
                    type="text"
                    onChange={urlHandler}
                />
                <button className="button">Download</button>
            </form>
        </div>
    )
}

export default Site
