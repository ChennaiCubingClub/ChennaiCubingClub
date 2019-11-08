import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ResultsService {

  constructor(private http: HttpClient) { }

  
  results: any = [
    {
      "title": "SRM Cube Open 2019",
      "result": [
        {
            "name": "Mohammed Aiman Koli",
            "score": "513.99"
        },
        {
            "name": "Nitin Nathan",
            "score": "483.46"
        },
        {
            "name": "Pranav Prabhu",
            "score": "476.37"
        },
        {
            "name": "Saravanan Gowthaman",
            "score": "456.4"
        },
        {
            "name": "Vijay Kishore",
            "score": "417.76"
        },
        {
            "name": "Sujai Shakthivel",
            "score": "391.4"
        },
        {
            "name": "Vishal Mohanraju",
            "score": "339.52"
        },
        {
            "name": "Lalith Sashank",
            "score": "337.16"
        },
        {
            "name": "Anirudh Sureshram",
            "score": "319.25"
        },
        {
            "name": "Sripad Sarma Katrapati",
            "score": "318.39"
        },
        {
            "name": "Anish Rajesh",
            "score": "260.86"
        },
        {
            "name": "Akash Rupela",
            "score": "235.32"
        },
        {
            "name": "Sandeep Rajaram",
            "score": "220.52"
        },
        {
            "name": "Emmanuel Rajapandian",
            "score": "183.17"
        },
        {
            "name": "Kunaal Parek",
            "score": "182.64"
        },
        {
            "name": "Aayush Sriram Bharadwaj",
            "score": "175.24"
        },
        {
            "name": "Sukesh Kumar",
            "score": "171.36"
        },
        {
            "name": "Nithin Babu",
            "score": "170.1"
        },
        {
            "name": "Daniel James",
            "score": "163.93"
        },
        {
            "name": "Imruthun Meenakshisundaram",
            "score": "135.18"
        },
        {
            "name": "Bhargav Narasimhan",
            "score": "132.79"
        },
        {
            "name": "Naren Ramesh",
            "score": "131.5"
        },
        {
            "name": "Adithyaa Anand",
            "score": "127.43"
        },
        {
            "name": "Hariharan Sachidanandam",
            "score": "121.99"
        },
        {
            "name": "Rahul Venkatesan",
            "score": "113.61"
        },
        {
            "name": "Hari Anirudh",
            "score": "100.86"
        },
        {
            "name": "Santosh Naranapatty",
            "score": "88.22"
        },
        {
            "name": "Atharva R. Bhat",
            "score": "82.11"
        },
        {
            "name": "Pritesh Lunkad",
            "score": "81.41"
        },
        {
            "name": "Naren Loganathan",
            "score": "81.31"
        },
        {
            "name": "Vishwanath Jeyaraman",
            "score": "74.2"
        },
        {
            "name": "Athul Gokuldas",
            "score": "72.98"
        },
        {
            "name": "Rajha Sirokshan",
            "score": "69.39"
        },
        {
            "name": "Pamulapati Sai Teja",
            "score": "67.56"
        },
        {
            "name": "Santhosh Sabarinathan",
            "score": "63.6"
        },
        {
            "name": "Mohan Balaji",
            "score": "57.87"
        },
        {
            "name": "Ashwin Ramesh",
            "score": "55.68"
        },
        {
            "name": "Nischal Nori",
            "score": "49.8"
        },
        {
            "name": "Hemvarshan Muthukumar",
            "score": "49.21"
        },
        {
            "name": "Lakshimi Rajaram",
            "score": "48.78"
        },
        {
            "name": "Hrithik Ramnath",
            "score": "48.63"
        },
        {
            "name": "Nitheesh Kumar Elangovan",
            "score": "45.24"
        },
        {
            "name": "Balaji Balamurugan",
            "score": "43.3"
        },
        {
            "name": "Nithish Raju",
            "score": "41.54"
        },
        {
            "name": "Vaishnav Nitesh",
            "score": "39.88"
        },
        {
            "name": "Ameya Anand Kamat",
            "score": "29.22"
        },
        {
            "name": "Mohammed Razal",
            "score": "28.8"
        },
        {
            "name": "Mithilesh Gopalakrishnan",
            "score": "28"
        },
        {
            "name": "Arvind Tatiparti",
            "score": "27.4"
        },
        {
            "name": "Rakesh M. Vaideeswaran",
            "score": "24.94"
        },
        {
            "name": "Khavin Janarthana",
            "score": "22.47"
        },
        {
            "name": "Ritvik Raj",
            "score": "22.47"
        },
        {
            "name": "Hari Pranav",
            "score": "21.06"
        },
        {
            "name": "Akshay Keswani",
            "score": "20.36"
        },
        {
            "name": "Keshav Sundararaman",
            "score": "19.95"
        },
        {
            "name": "Daniel Koilpillai",
            "score": "19.12"
        },
        {
            "name": "Shobhan Karthish",
            "score": "17.26"
        },
        {
            "name": "Arun Krishnan",
            "score": "16.52"
        },
        {
            "name": "Kishore Kumar",
            "score": "15.5"
        },
        {
            "name": "Pradeep Sudakar",
            "score": "14.2"
        },
        {
            "name": "Adithyaa Hariharan",
            "score": "13.75"
        },
        {
            "name": "Pradhyun Naresh",
            "score": "13.66"
        },
        {
            "name": "Jagadish Muthuram",
            "score": "12.6"
        },
        {
            "name": "Vani Muthukrishnan",
            "score": "12.34"
        },
        {
            "name": "Ramanathan Venkatachalam",
            "score": "11.35"
        },
        {
            "name": "Sachin Arvind",
            "score": "11.18"
        },
        {
            "name": "Thabares Nazeer Basha",
            "score": "10.22"
        },
        {
            "name": "Akunuri Sri Abilash",
            "score": "10.21"
        },
        {
            "name": "Sree Vathsan",
            "score": "10.08"
        },
        {
            "name": "Kailash Anand",
            "score": "9.76"
        },
        {
            "name": "Arjun Venkatesh",
            "score": "9.61"
        },
        {
            "name": "Sneha Koodalingam",
            "score": "9.36"
        },
        {
            "name": "Arulkumaran Balaji",
            "score": "9.36"
        },
        {
            "name": "Vikram Gopinath",
            "score": "9.12"
        },
        {
            "name": "Harihanth Kumar",
            "score": "9.04"
        },
        {
            "name": "Mohammed Rushaan",
            "score": "8.88"
        },
        {
            "name": "Karthikeyan Tamilarasan",
            "score": "8.34"
        },
        {
            "name": "Pranav R. Mallya",
            "score": "8.34"
        },
        {
            "name": "Arun Seshadhri",
            "score": "7.8"
        },
        {
            "name": "Nandha Kumar",
            "score": "7.74"
        },
        {
            "name": "Sarvesh Suryanarayan",
            "score": "7.05"
        },
        {
            "name": "Sanjeev Kumar",
            "score": "6.4"
        },
        {
            "name": "Naganesan Thirunavukarasu",
            "score": "5.72"
        },
        {
            "name": "Prashanth Swaminathan",
            "score": "5.4"
        },
        {
            "name": "Rithesh Subbulakshmi Saravanakumar",
            "score": "4.88"
        },
        {
            "name": "Anirudh Yamunan Govindarajan",
            "score": "2.44"
        },
        {
            "name": "Lakshana Satish Kumar",
            "score": "2.34"
        },
        {
            "name": "Kathiresan Chellappan",
            "score": "1.42"
        },
        {
            "name": "Nitin Kumar",
            "score": "1.42"
        },
        {
            "name": "Kshitij Dhara",
            "score": "1.38"
        },
        {
            "name": "Logesh Santhosh",
            "score": "1.22"
        },
        {
            "name": "Dharshana Satish Kumar",
            "score": "1.17"
        }
    ]
    }
  ]
  getResults(){
    return this.results;
  }
}
