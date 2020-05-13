import React, { Component } from 'react';
import { VerticalTimeline, VerticalTimelineElement } from 'react-vertical-timeline-component';
import 'react-vertical-timeline-component/style.min.css';
import './mycss2.css';
import { FaCanadianMapleLeaf as MapleLeaf } from 'react-icons/fa';
import VerticalTimelineContent from './VerticalTimelineContent1';
import axios from 'axios';
import { uniqueIdGenerator, sortDates, dateLabel } from './Helpers';
//import queryString from 'query-string';

export class MyComponent2 extends Component {

    constructor(props) {
        super(props);
        this.state = { title: "", data: [] };
    }
    fetchData = () => {
        let url = 'https://raw.githubusercontent.com/hirak1984/VariousTimelines/master/data/json/CanadaHistory.json';
        axios.get(url)
            .then(res => {
                const data = res.data;
                console.log(data);
                console.log(data.Title);
                console.log(data.headers);
                this.setState({ title: data.Title, data: data.headers });
            }).catch(err => console.log(err))
    };

    componentDidMount() {
        this.fetchData();
    }
    render() {
        //const {sortOrder} = queryString.parse(this.props.location.search)

        return (
            <div className="container">
                <div className="leftpane">
                        &nbsp;
                       
                </div>
                <div className="middlepane">
                    <center > < h3 > {this.state.title} </h3></center> < hr />
                    <VerticalTimeline > {
                        this.state.data.sort((d1, d2) => sortDates(d1.DateFrom, d2.DateFrom, '')).map(d => {
                            return (< VerticalTimelineElement className="vertical-timeline-element--work"
                                contentStyle={styles.contentstyle}
                                contentArrowStyle={styles.contentArrowStyle}
                                date={dateLabel(d.DateFrom, d.DateTo)}
                                key={uniqueIdGenerator(d.DateFrom)}
                                iconStyle={
                                    { background: '#EF3340', color: '#fff' }
                                }
                                icon={< MapleLeaf/>} >
                                <VerticalTimelineContent key={uniqueIdGenerator(d.Title)}
                                    title={d.Title}
                                    subHeaders={d.subHeaders} />
                            </VerticalTimelineElement >
                            )
                        })
                    }

                    </VerticalTimeline>
                </div >
                <div className="rightpane">&nbsp;</div>
            </div>
        )
    }
}

  const styles= {
    contentArrowStyle: {
        borderRight: '20px solid  #EF3340'
    },
    contentstyle: {
        background:'#fff', 
        border: '2px solid  #EF3340',
        color: '#000' ,
        mozBoxShadow:'1px 1px 5px rgba(33,33,33,1)',
        webkitBoxShadow: '1px 1px 5px rgba(33,33,33,.7)',
        boxShadow: '1px 1px 5px rgba(33,33,33,.7)'
    }
};
export default MyComponent2;