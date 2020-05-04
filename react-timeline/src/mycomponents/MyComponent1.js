import React, { Component } from 'react';
import { VerticalTimeline, VerticalTimelineElement } from 'react-vertical-timeline-component';
import 'react-vertical-timeline-component/style.min.css';
import { GiMapleLeaf } from 'react-icons/gi';
import VerticalTimelineContent from './VerticalTimelineContent';
import axios from 'axios';
import {uniqueIdGenerator,sortDates} from './Helpers';
import queryString from 'query-string';

export class MyComponent1 extends Component {

    constructor(props) {
        super(props);
        this.state = { data: []};
    }
    fetchData = ()=>{
        let url='https://gist.githubusercontent.com/hirak1984/33f68bf827e4b5f111aa26e71781c2c7/raw/e1cfa0dcd90245ec3e4f693dd0b94defd6691b48/canada_timeline.json';
       // let url='https://raw.githubusercontent.com/hirak1984/VariousTimelines/master/data/testformat.json';
        axios.get(url)
        .then(res => {
          const data = res.data.data;
          console.log(data);
          this.setState({ data });
        }).catch(err=>console.log(err))
    };

    componentDidMount() {
        this.fetchData();
    }
    render() {
        const {sortOrder} = queryString.parse(this.props.location.search)
        return (
            <VerticalTimeline>
                {
                    this.state.data.sort((d1,d2)=>sortDates(d1,d2,sortOrder)).map(d => {
                        return (<VerticalTimelineElement
                            className="vertical-timeline-element--work"
                            date={d.dateText}
                            key={uniqueIdGenerator(d.dateText)}
                            iconStyle={{ background: 'rgb(233, 30, 99)', color: '#fff' }}
                            icon={<GiMapleLeaf />}
                        >
                            <VerticalTimelineContent title={d.title} subtitle={d.subtitle} details={d.texts} />
                        </VerticalTimelineElement>
                        )
                    })


                }
            </VerticalTimeline>
        )
    }
}

export default MyComponent1;
