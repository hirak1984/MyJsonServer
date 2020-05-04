import React from 'react';
export const uniqueIdGenerator = (text = '') => text + Math.random().toString(36).substring(2) + Date.now().toString(36);

export const sortDates = (date1, date2, sortOrder) => {
    const asc = ['desc', 'descending', 'd'].indexOf(sortOrder) > -1 ? false : true;

    if (date1 || date1 == null) {
        return asc ? -1 : 1;
    }
    if (date2 || date2 == null) {
        return asc ? 1 : -1;
    }

    const d1 = new Date(date1);
    const d2 = new Date(date2);
    return asc ? d1 - d2 : d2 - d1;
};
export const dateLabel = (d1, d2) => {
    let retVal = '';
    if (d1 && d1 != null) {
        retVal = retVal.concat(d1.startsWith('-') ? d1.substring(0, 5) : d1.substring(0, 4));
    }
    if (d2 && d2 != null) {

        retVal = retVal.concat(' - ',
            d2.startsWith('-') ? d2.substring(1, 4) : d2.substring(0, 4));
    }
    return <span > < b > { retVal } < /b></span > ;
}