import React from 'react'
import { uniqueIdGenerator, sortDates, dateLabel } from './Helpers';
import ReactHtmlParser from 'react-html-parser'; 
const VerticalTimelineContent1 = ({ title, subHeaders }) => {
    return (
        <div key = {uniqueIdGenerator(title)}>
            <center><h3 className="vertical-timeline-element-title">{title}</h3></center>
            {subHeaders.sort((sh1, sh2) => sortDates(sh1.DateFrom, sh2.DateFrom, '')).map(sh => {
                return (<>
                    <h4  className="vertical-timeline-element-subtitle">{sh.Title}</h4>
                    <h5  className="vertical-timeline-element-subtitle">{dateLabel(sh.DateFrom, sh.DateTo)}</h5>
               <ul> {sh.Texts.map(detail => <li key={uniqueIdGenerator(detail)} >{ReactHtmlParser (detail) }</li>)}</ul>
                </>)
            })
            }
        </div>
    )
}

export default VerticalTimelineContent1;
