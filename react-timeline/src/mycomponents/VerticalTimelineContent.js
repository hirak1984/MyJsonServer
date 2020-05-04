import React from 'react'
import { uniqueIdGenerator, sortDates,dateLabel } from './Helpers';

const VerticalTimelineContent = ({ title, subtitle, details }) =>{
    return (
        <>
            <h3 className="vertical-timeline-element-title">{title}</h3>
            <h4 className="vertical-timeline-element-subtitle">{subtitle}</h4>
            {details}
        </>
    )
}

export default VerticalTimelineContent;
