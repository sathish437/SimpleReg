import React from 'react'
import {createPortal} from 'react-dom'
const cartList = ({isAdd,onClose,children}) => {
    if (!isAdd) return null;
    return createPortal(
            <div>
                {children}
                <button onClick={onClose}>X</button>
            </div>,
            document.body
    )
}

export default cartList
