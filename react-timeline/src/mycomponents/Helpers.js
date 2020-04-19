export const uniqueIdGenerator = (text='')=>text+Math.random().toString(36).substring(2) + Date.now().toString(36);

export const sortDates = (data1, data2, asc) => {
    const date1 = new Date(data1.dateText);
    const date2 = new Date(data2.dateText);
    return asc? date1 - date2: date2-date1;
  };
  export const sortDatesAsc = (data1, data2) => sortDates(data1,data2,true);
  export const sortDatesDesc = (data1, data2) => sortDates(data1,data2,false)