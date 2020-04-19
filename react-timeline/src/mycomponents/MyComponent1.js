import React, { Component } from 'react'
import { VerticalTimeline, VerticalTimelineElement } from 'react-vertical-timeline-component';
import 'react-vertical-timeline-component/style.min.css';
import { GiMapleLeaf } from 'react-icons/gi';
import VerticalTimelineContent from './VerticalTimelineContent';
import axios from 'axios';
import {uniqueIdGenerator,sortDatesAsc as sortDates} from './Helpers';

export class MyComponent1 extends Component {

    constructor(props) {
        super(props);
        this.state = { data: []};
    }
    fetchData = ()=>{
        axios.get('https://raw.githubusercontent.com/hirak1984/VariousTimelines/master/data/testformat.json')
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
        return (
            <VerticalTimeline>
                {
                    this.state.data.sort(sortDates).map(d => {
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
