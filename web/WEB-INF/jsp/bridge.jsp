<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 19-4-27
  Time: 下午3:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>主动探测到的网桥节点</title>
    <script type="text/javascript" src="${pageScope.request.ContextPath}/js/jquery-1.9.1.min.js"></script>
    <!-- <script type="text/javascript" src="back-endPage.js"></script> --> <!-- 后台分页 -->
    <script type="text/javascript" src="${pageScope.request.ContextPath}/js/front-endPage.js"></script><!-- 前台分页 -->

    <link rel="stylesheet" type="text/css" href="${pageScope.request.ContextPath}/css/table.css">
    <link rel="stylesheet" type="text/css" href="${pageScope.request.ContextPath}/css/init.css">
    <style>
        body {
            background-color: #ffffff;
        }
        input[type="text"] {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            width: 100%;
            height: -webkit-calc(3em + 2px);
            height: calc(3em + 2px);
            margin: 0 0 1em;
            padding: 1em;
            border: 1px solid #096dcc;
            border-radius: 1.5em;
            background: #fcfef7;
            resize: none;
            outline: none;
        }
        input[type="submit"] {
            -webkit-box-sizing: border-box;
            -moz-box-sizing: border-box;
            box-sizing: border-box;
            width: 100%;
            height: -webkit-calc(3em + 2px);
            height: calc(3em + 2px);
            margin: 0 0 1em;
            padding: 1em;
            border: 1px solid #cccccc;
            border-radius: 1.5em;
            background: #fff;
            resize: none;
            outline: none;
        }
        input[type="text"][required]:focus {
            border-color: #ffffff;
        }
        input[type="text"][required]:focus + label[placeholder]:before {
            color: #ffffff;
        }
        input[type="text"][required]:focus + label[placeholder]:before,
        input[type="text"][required]:valid + label[placeholder]:before {
            -webkit-transition-duration: .2s;
            transition-duration: .2s;
            -webkit-transform: translate(0, -1.5em) scale(0.9, 0.9);
            -ms-transform: translate(0, -1.5em) scale(0.9, 0.9);
            transform: translate(0, -1.5em) scale(0.9, 0.9);
        }
        input[type="text"][required]:invalid + label[placeholder][alt]:before {
            content: attr(alt);
        }
        input[type="text"][required] + label[placeholder] {
            display: block;
            pointer-events: none;
            line-height: 2.3em;
            margin-top: -webkit-calc(-3em - 2px);
            margin-top: calc(-3em - 2px);
            margin-bottom: -webkit-calc((3em - 1em) + 2px);
            margin-bottom: calc((3em - 1em) + 2px);
        }
        input[type="text"][required] + label[placeholder]:before {
            content: attr(placeholder);
            display: inline-block;
            margin: 0 -webkit-calc(1em + 2px);
            margin: 0 calc(1em + 2px);
            padding: 0 2px;
            color: #898989;
            white-space: nowrap;
            -webkit-transition: 0.3s ease-in-out;
            transition: 0.3s ease-in-out;
            background-image: -webkit-gradient(linear, left top, left bottom, from(#ffffff), to(#ffffff));
            background-image: -webkit-linear-gradient(top, #ffffff, #ffffff);
            background-image: linear-gradient(to bottom, #ffffff, #ffffff);
            -webkit-background-size: 100% 5px;
            background-size: 100% 5px;
            background-repeat: no-repeat;
            background-position: center;
        }
    </style>

</head>
<h1 align="center">主动探测到的网桥节点</h1>
<body>




<style>
    #chartdiv {
        width: 100%;
        height: 500px;
    }

</style>

<!-- Resources -->
<script src="${pageScope.request.ContextPath}/js/lib/4/core.js"></script>
<script src="${pageScope.request.ContextPath}/js/lib/4/maps.js"></script>
<script src="${pageScope.request.ContextPath}/js/lib/4/geodata/worldLow.js"></script>
<script src="${pageScope.request.ContextPath}/js/lib/4/themes/animated.js"></script>

<!-- Chart code -->
<script>
    am4core.ready(function() {

// Themes begin
        am4core.useTheme(am4themes_animated);
// Themes end

        /**
         * Define SVG path for target icon
         */
        var targetSVG = "M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9";

// Create map instance
        var chart = am4core.create("chartdiv", am4maps.MapChart);

// Set map definition
        chart.geodata = am4geodata_worldLow;

// Set projection
        chart.projection = new am4maps.projections.Miller();

// Create map polygon series
        var polygonSeries = chart.series.push(new am4maps.MapPolygonSeries());

// Exclude Antartica
        polygonSeries.exclude = ["AQ"];

// Make map load polygon (like country names) data from GeoJSON
        polygonSeries.useGeodata = true;

// Configure series
        var polygonTemplate = polygonSeries.mapPolygons.template;
        polygonTemplate.strokeOpacity = 0.5;
        polygonTemplate.nonScalingStroke = true;

// create capital markers
        var imageSeries = chart.series.push(new am4maps.MapImageSeries());

// define template
        var imageSeriesTemplate = imageSeries.mapImages.template;
        var circle = imageSeriesTemplate.createChild(am4core.Sprite);
        circle.scale = 0.4;
        circle.fill = new am4core.InterfaceColorSet().getFor("alternativeBackground");
        circle.path = targetSVG;
// what about scale...

// set propertyfields
        imageSeriesTemplate.propertyFields.latitude = "latitude";
        imageSeriesTemplate.propertyFields.longitude = "longitude";

        imageSeriesTemplate.horizontalCenter = "middle";
        imageSeriesTemplate.verticalCenter = "middle";
        imageSeriesTemplate.align = "center";
        imageSeriesTemplate.valign = "middle";
        imageSeriesTemplate.width = 8;
        imageSeriesTemplate.height = 8;
        imageSeriesTemplate.nonScaling = true;
        imageSeriesTemplate.tooltipText = "{title}";
        imageSeriesTemplate.fill = am4core.color("#000");
        imageSeriesTemplate.background.fillOpacity = 0;
        imageSeriesTemplate.background.fill = am4core.color("#ffffff");
        imageSeriesTemplate.setStateOnChildren = true;
        imageSeriesTemplate.states.create("hover");

        imageSeries.data = [
            {
                "ip" : "144.76.97.34",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "37.252.185.86",
                "longitude" : 16.3667,
                "latitude" : 48.2
            },
            {
                "ip" : "162.158.69.41",
                "longitude" : -122.964,
                "latitude" : 45.5397
            },
            {
                "ip" : "54.38.55.13",
                "longitude" : 21.0362,
                "latitude" : 52.2394
            },
            {
                "ip" : "141.51.125.1",
                "longitude" : 9.4912,
                "latitude" : 51.3166
            },
            {
                "ip" : "209.141.35.208",
                "longitude" : -115.116,
                "latitude" : 36.1685
            },
            {
                "ip" : "178.62.244.102",
                "longitude" : 4.9392,
                "latitude" : 52.352
            },
            {
                "ip" : "162.158.67.92",
                "longitude" : -122.964,
                "latitude" : 45.5397
            },
            {
                "ip" : "151.80.7.124",
                "longitude" : 3.178,
                "latitude" : 50.6974
            },
            {
                "ip" : "45.76.32.13",
                "longitude" : 4.6568,
                "latitude" : 52.3902
            },
            {
                "ip" : "66.220.149.44",
                "longitude" : -97.822,
                "latitude" : 37.751
            },
            {
                "ip" : "84.9.71.105",
                "longitude" : -2.55,
                "latitude" : 52.7167
            },
            {
                "ip" : "92.206.247.198",
                "longitude" : 12.884,
                "latitude" : 50.8213
            },
            {
                "ip" : "192.99.5.109",
                "longitude" : -73.5794,
                "latitude" : 45.5063
            },
            {
                "ip" : "178.63.47.145",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "89.23.224.57",
                "longitude" : 12.5589,
                "latitude" : 55.6786
            },
            {
                "ip" : "194.34.133.20",
                "longitude" : 24.8708,
                "latitude" : 60.2188
            },
            {
                "ip" : "144.217.20.138",
                "longitude" : -73.8736,
                "latitude" : 45.3161
            },
            {
                "ip" : "109.105.109.163",
                "longitude" : 12.0564,
                "latitude" : 55.7123
            },
            {
                "ip" : "204.93.130.141",
                "longitude" : -87.6376,
                "latitude" : 41.8777
            },
            {
                "ip" : "188.226.97.201",
                "longitude" : 60.6122,
                "latitude" : 56.8519
            },
            {
                "ip" : "83.173.221.61",
                "longitude" : 8.6436,
                "latitude" : 47.6905
            },
            {
                "ip" : "209.141.48.198",
                "longitude" : -115.116,
                "latitude" : 36.1685
            },
            {
                "ip" : "51.68.206.67",
                "longitude" : -0.1224,
                "latitude" : 51.4964
            },
            {
                "ip" : "109.105.109.164",
                "longitude" : 12.0564,
                "latitude" : 55.7123
            },
            {
                "ip" : "221.118.119.246",
                "longitude" : 137.15,
                "latitude" : 35.0833
            },
            {
                "ip" : "162.158.70.153",
                "longitude" : -122.964,
                "latitude" : 45.5397
            },
            {
                "ip" : "172.69.25.16",
                "longitude" : -122.964,
                "latitude" : 45.5397
            },
            {
                "ip" : "162.158.69.38",
                "longitude" : -122.964,
                "latitude" : 45.5397
            },
            {
                "ip" : "193.31.26.221",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "5.3.2.3",
                "longitude" : 56.2634,
                "latitude" : 57.8499
            },
            {
                "ip" : "6.2.59.3",
                "longitude" : -97.822,
                "latitude" : 37.751
            },
            {
                "ip" : "37.218.245.14",
                "longitude" : 4.8995,
                "latitude" : 52.3824
            },
            {
                "ip" : "172.69.25.200",
                "longitude" : -122.964,
                "latitude" : 45.5397
            },
            {
                "ip" : "172.69.25.134",
                "longitude" : -122.964,
                "latitude" : 45.5397
            },
            {
                "ip" : "162.158.69.88",
                "longitude" : -122.964,
                "latitude" : 45.5397
            },
            {
                "ip" : "94.242.249.13",
                "longitude" : 6.1667,
                "latitude" : 49.75
            },
            {
                "ip" : "193.182.111.8",
                "longitude" : 18.056,
                "latitude" : 59.3247
            },
            {
                "ip" : "94.242.249.11",
                "longitude" : 6.1667,
                "latitude" : 49.75
            },
            {
                "ip" : "98.111.246.204",
                "longitude" : -79.9247,
                "latitude" : 40.4324
            },
            {
                "ip" : "91.194.60.100",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "193.11.166.194",
                "longitude" : 18.0776,
                "latitude" : 59.5344
            },
            {
                "ip" : "78.46.56.169",
                "longitude" : 11.0683,
                "latitude" : 49.4475
            },
            {
                "ip" : "193.29.56.116",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "162.158.70.30",
                "longitude" : -122.964,
                "latitude" : 45.5397
            },
            {
                "ip" : "144.76.236.140",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "185.120.77.92",
                "longitude" : 73.0969,
                "latitude" : 49.8119
            },
            {
                "ip" : "5.45.101.108",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "162.158.69.158",
                "longitude" : -122.964,
                "latitude" : 45.5397
            },
            {
                "ip" : "74.125.189.99",
                "longitude" : -97.822,
                "latitude" : 37.751
            },
            {
                "ip" : "109.195.183.171",
                "longitude" : 37.6111,
                "latitude" : 54.2044
            },
            {
                "ip" : "103.204.209.114",
                "longitude" : 90.4085,
                "latitude" : 23.7257
            },
            {
                "ip" : "71.113.187.235",
                "longitude" : -76.9331,
                "latitude" : 40.2327
            },
            {
                "ip" : "5.249.146.133",
                "longitude" : 11.8783,
                "latitude" : 43.4631
            },
            {
                "ip" : "144.76.165.237",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "107.182.225.239",
                "longitude" : -74.0014,
                "latitude" : 40.7503
            },
            {
                "ip" : "134.209.171.119",
                "longitude" : -74.1403,
                "latitude" : 40.8364
            },
            {
                "ip" : "108.39.229.146",
                "longitude" : -79.9558,
                "latitude" : 40.4735
            },
            {
                "ip" : "185.203.114.242",
                "longitude" : 8.1551,
                "latitude" : 47.1449
            },
            {
                "ip" : "38.229.33.83",
                "longitude" : -97.822,
                "latitude" : 37.751
            },
            {
                "ip" : "216.252.162.21",
                "longitude" : -97.822,
                "latitude" : 37.751
            },
            {
                "ip" : "195.113.199.102",
                "longitude" : 16.6442,
                "latitude" : 49.2327
            },
            {
                "ip" : "78.69.12.229",
                "longitude" : 12.0761,
                "latitude" : 57.4872
            },
            {
                "ip" : "80.240.216.253",
                "longitude" : 37.6172,
                "latitude" : 55.7527
            },
            {
                "ip" : "185.220.101.31",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "78.129.218.56",
                "longitude" : -0.1224,
                "latitude" : 51.4964
            },
            {
                "ip" : "82.64.82.111",
                "longitude" : 2.8602,
                "latitude" : 48.9411
            },
            {
                "ip" : "84.239.153.105",
                "longitude" : 24.8708,
                "latitude" : 60.2188
            },
            {
                "ip" : "37.200.99.251",
                "longitude" : 7.0122,
                "latitude" : 51.4476
            },
            {
                "ip" : "51.254.147.61",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "213.109.160.48",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "51.15.77.244",
                "longitude" : 4.8995,
                "latitude" : 52.3824
            },
            {
                "ip" : "152.89.239.49",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "205.185.121.209",
                "longitude" : -121.891,
                "latitude" : 37.3387
            },
            {
                "ip" : "159.69.250.201",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "185.96.88.164",
                "longitude" : 12.0564,
                "latitude" : 55.7123
            },
            {
                "ip" : "212.83.139.137",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "90.231.75.5",
                "longitude" : 13.1569,
                "latitude" : 55.3751
            },
            {
                "ip" : "51.79.52.15",
                "longitude" : -79.3716,
                "latitude" : 43.6319
            },
            {
                "ip" : "92.222.22.37",
                "longitude" : 2.3281,
                "latitude" : 48.8607
            },
            {
                "ip" : "51.15.117.50",
                "longitude" : 5.7797,
                "latitude" : 50.905
            },
            {
                "ip" : "185.220.101.32",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "185.221.172.60",
                "longitude" : 12.1097,
                "latitude" : 43.1479
            },
            {
                "ip" : "81.17.17.130",
                "longitude" : 8.1551,
                "latitude" : 47.1449
            },
            {
                "ip" : "46.165.250.224",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "192.99.42.34",
                "longitude" : -73.5794,
                "latitude" : 45.5063
            },
            {
                "ip" : "193.35.52.53",
                "longitude" : 10.75,
                "latitude" : 59.95
            },
            {
                "ip" : "81.16.33.30",
                "longitude" : 16.3667,
                "latitude" : 48.2
            },
            {
                "ip" : "78.47.178.19",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "217.79.178.60",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "199.249.230.68",
                "longitude" : -97.822,
                "latitude" : 37.751
            },
            {
                "ip" : "82.251.109.9",
                "longitude" : 2.3527,
                "latitude" : 48.8543
            },
            {
                "ip" : "185.86.150.58",
                "longitude" : 25,
                "latitude" : 57
            },
            {
                "ip" : "199.195.250.77",
                "longitude" : -78.8784,
                "latitude" : 42.8864
            },
            {
                "ip" : "144.76.143.137",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "138.197.202.35",
                "longitude" : -121.975,
                "latitude" : 37.3417
            },
            {
                "ip" : "195.123.209.7",
                "longitude" : 25,
                "latitude" : 57
            },
            {
                "ip" : "78.82.250.133",
                "longitude" : 18.1833,
                "latitude" : 59.2667
            },
            {
                "ip" : "173.239.79.203",
                "longitude" : -122.273,
                "latitude" : 37.8922
            },
            {
                "ip" : "176.31.43.51",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "91.109.21.148",
                "longitude" : 6.7278,
                "latitude" : 49.3556
            },
            {
                "ip" : "116.203.22.86",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "80.127.116.96",
                "longitude" : 4.8995,
                "latitude" : 52.3824
            },
            {
                "ip" : "137.74.116.214",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "94.230.208.148",
                "longitude" : 8.1551,
                "latitude" : 47.1449
            },
            {
                "ip" : "199.249.230.79",
                "longitude" : -97.822,
                "latitude" : 37.751
            },
            {
                "ip" : "81.17.27.136",
                "longitude" : 8.1551,
                "latitude" : 47.1449
            },
            {
                "ip" : "95.90.95.22",
                "longitude" : 13.6605,
                "latitude" : 51.1065
            },
            {
                "ip" : "185.97.32.34",
                "longitude" : 18.056,
                "latitude" : 59.3247
            },
            {
                "ip" : "108.28.249.114",
                "longitude" : -77.3909,
                "latitude" : 39.0503
            },
            {
                "ip" : "93.90.204.219",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "173.249.42.54",
                "longitude" : 11.1617,
                "latitude" : 49.405
            },
            {
                "ip" : "163.172.47.34",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "167.86.127.29",
                "longitude" : 11.1617,
                "latitude" : 49.405
            },
            {
                "ip" : "213.163.70.234",
                "longitude" : 4.8995,
                "latitude" : 52.3824
            },
            {
                "ip" : "157.245.78.93",
                "longitude" : 4.9392,
                "latitude" : 52.352
            },
            {
                "ip" : "192.42.116.14",
                "longitude" : 4.9167,
                "latitude" : 52.35
            },
            {
                "ip" : "210.215.135.2",
                "longitude" : 151.23,
                "latitude" : -33.8324
            },
            {
                "ip" : "5.135.187.5",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "178.17.170.96",
                "longitude" : 28.8573,
                "latitude" : 47.0052
            },
            {
                "ip" : "95.216.115.85",
                "longitude" : 24.9375,
                "latitude" : 60.1708
            },
            {
                "ip" : "46.22.212.230",
                "longitude" : 24.7323,
                "latitude" : 59.433
            },
            {
                "ip" : "93.95.100.170",
                "longitude" : 37.8256,
                "latitude" : 55.9142
            },
            {
                "ip" : "165.227.174.150",
                "longitude" : 8.6843,
                "latitude" : 50.1188
            },
            {
                "ip" : "212.24.100.138",
                "longitude" : 24,
                "latitude" : 56
            },
            {
                "ip" : "185.4.132.183",
                "longitude" : 22.8075,
                "latitude" : 37.5636
            },
            {
                "ip" : "195.154.209.91",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "193.200.241.195",
                "longitude" : 11.6074,
                "latitude" : 48.1089
            },
            {
                "ip" : "51.15.54.117",
                "longitude" : 4.6419,
                "latitude" : 52.3615
            },
            {
                "ip" : "158.255.7.61",
                "longitude" : 37.6068,
                "latitude" : 55.7386
            },
            {
                "ip" : "80.211.76.120",
                "longitude" : 11.8783,
                "latitude" : 43.4631
            },
            {
                "ip" : "147.135.78.157",
                "longitude" : -77.3429,
                "latitude" : 38.9609
            },
            {
                "ip" : "144.76.118.86",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "181.119.30.26",
                "longitude" : -74.0617,
                "latitude" : 4.6493
            },
            {
                "ip" : "5.189.142.118",
                "longitude" : 11.1617,
                "latitude" : 49.405
            },
            {
                "ip" : "2.139.124.15",
                "longitude" : -0.6167,
                "latitude" : 38.8167
            },
            {
                "ip" : "93.177.66.249",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "176.31.45.3",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "91.219.237.244",
                "longitude" : 19.0791,
                "latitude" : 47.4977
            },
            {
                "ip" : "145.239.233.66",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "185.100.84.212",
                "longitude" : 26.1691,
                "latitude" : 44.418
            },
            {
                "ip" : "94.23.194.134",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "209.141.34.95",
                "longitude" : -115.116,
                "latitude" : 36.1685
            },
            {
                "ip" : "167.71.248.136",
                "longitude" : -74.1403,
                "latitude" : 40.8364
            },
            {
                "ip" : "24.154.178.195",
                "longitude" : -79.93,
                "latitude" : 40.8795
            },
            {
                "ip" : "104.194.228.240",
                "longitude" : -118.427,
                "latitude" : 34.413
            },
            {
                "ip" : "93.215.31.213",
                "longitude" : 10.9185,
                "latitude" : 49.8883
            },
            {
                "ip" : "37.187.96.78",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "95.216.209.193",
                "longitude" : 24.9375,
                "latitude" : 60.1708
            },
            {
                "ip" : "185.21.216.197",
                "longitude" : -0.1224,
                "latitude" : 51.4964
            },
            {
                "ip" : "23.129.64.164",
                "longitude" : -122.325,
                "latitude" : 47.6008
            },
            {
                "ip" : "213.157.15.235",
                "longitude" : 8.5316,
                "latitude" : 49.5121
            },
            {
                "ip" : "91.37.145.146",
                "longitude" : 8.4647,
                "latitude" : 49.4883
            },
            {
                "ip" : "185.220.101.45",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "92.252.174.88",
                "longitude" : 48.4,
                "latitude" : 54.3333
            },
            {
                "ip" : "23.129.64.167",
                "longitude" : -122.325,
                "latitude" : 47.6008
            },
            {
                "ip" : "195.154.250.239",
                "longitude" : 2.3527,
                "latitude" : 48.8543
            },
            {
                "ip" : "108.161.139.183",
                "longitude" : -75.4339,
                "latitude" : 40.1922
            },
            {
                "ip" : "190.2.144.104",
                "longitude" : 4.2182,
                "latitude" : 51.9949
            },
            {
                "ip" : "173.249.8.113",
                "longitude" : 11.1617,
                "latitude" : 49.405
            },
            {
                "ip" : "130.180.51.242",
                "longitude" : 8.6596,
                "latitude" : 50.1155
            },
            {
                "ip" : "199.249.230.115",
                "longitude" : -97.822,
                "latitude" : 37.751
            },
            {
                "ip" : "157.245.155.204",
                "longitude" : -74,
                "latitude" : 40.7157
            },
            {
                "ip" : "91.78.207.121",
                "longitude" : 37.6172,
                "latitude" : 55.7527
            },
            {
                "ip" : "178.199.228.198",
                "longitude" : 7.5733,
                "latitude" : 47.5584
            },
            {
                "ip" : "188.165.42.181",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "217.199.207.250",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "95.215.46.46",
                "longitude" : 18.056,
                "latitude" : 59.3247
            },
            {
                "ip" : "212.47.239.83",
                "longitude" : 2.3281,
                "latitude" : 48.8607
            },
            {
                "ip" : "185.217.95.25",
                "longitude" : 4.8995,
                "latitude" : 52.3824
            },
            {
                "ip" : "89.31.57.58",
                "longitude" : 4.8995,
                "latitude" : 52.3824
            },
            {
                "ip" : "136.243.70.198",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "37.187.12.100",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "84.31.172.51",
                "longitude" : 5.208,
                "latitude" : 51.5711
            },
            {
                "ip" : "37.28.154.68",
                "longitude" : 21.0362,
                "latitude" : 52.2394
            },
            {
                "ip" : "93.115.26.181",
                "longitude" : 24,
                "latitude" : 56
            },
            {
                "ip" : "87.236.212.128",
                "longitude" : -2.1959,
                "latitude" : 53.5039
            },
            {
                "ip" : "51.15.54.182",
                "longitude" : 4.6419,
                "latitude" : 52.3615
            },
            {
                "ip" : "86.130.147.141",
                "longitude" : -2.1658,
                "latitude" : 53.0278
            },
            {
                "ip" : "192.42.116.26",
                "longitude" : 4.9167,
                "latitude" : 52.35
            },
            {
                "ip" : "172.104.27.216",
                "longitude" : -75.1474,
                "latitude" : 39.9476
            },
            {
                "ip" : "95.216.54.12",
                "longitude" : 24.9375,
                "latitude" : 60.1708
            },
            {
                "ip" : "51.91.57.98",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "64.71.142.240",
                "longitude" : -97.822,
                "latitude" : 37.751
            },
            {
                "ip" : "149.56.44.47",
                "longitude" : -73.5794,
                "latitude" : 45.5063
            },
            {
                "ip" : "5.196.23.64",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "163.172.25.118",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "51.15.249.81",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "159.69.38.44",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "62.219.3.47",
                "longitude" : 34.75,
                "latitude" : 31.5
            },
            {
                "ip" : "185.165.242.5",
                "longitude" : 4.8995,
                "latitude" : 52.3824
            },
            {
                "ip" : "138.201.254.83",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "209.240.109.238",
                "longitude" : -73.7527,
                "latitude" : 42.747
            },
            {
                "ip" : "162.247.74.216",
                "longitude" : -74.0018,
                "latitude" : 40.6521
            },
            {
                "ip" : "149.56.130.157",
                "longitude" : -73.5794,
                "latitude" : 45.5063
            },
            {
                "ip" : "178.18.122.109",
                "longitude" : -0.8922,
                "latitude" : 52.425
            },
            {
                "ip" : "51.83.254.153",
                "longitude" : 21.0362,
                "latitude" : 52.2394
            },
            {
                "ip" : "66.70.211.20",
                "longitude" : -73.5794,
                "latitude" : 45.5063
            },
            {
                "ip" : "195.176.3.24",
                "longitude" : 8.5462,
                "latitude" : 47.0476
            },
            {
                "ip" : "95.154.24.156",
                "longitude" : 8.2876,
                "latitude" : 55.6268
            },
            {
                "ip" : "51.15.235.73",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "213.183.63.36",
                "longitude" : 23.3333,
                "latitude" : 42.7
            },
            {
                "ip" : "136.243.101.164",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "51.15.91.78",
                "longitude" : 4.8995,
                "latitude" : 52.3824
            },
            {
                "ip" : "199.184.246.250",
                "longitude" : -97.822,
                "latitude" : 37.751
            },
            {
                "ip" : "185.220.101.66",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "185.107.47.215",
                "longitude" : 4.8995,
                "latitude" : 52.3824
            },
            {
                "ip" : "136.243.149.82",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "46.9.242.214",
                "longitude" : 10.7747,
                "latitude" : 59.8468
            },
            {
                "ip" : "131.188.40.188",
                "longitude" : 11.0087,
                "latitude" : 49.5988
            },
            {
                "ip" : "50.236.201.218",
                "longitude" : -71.1034,
                "latitude" : 42.3797
            },
            {
                "ip" : "213.61.215.54",
                "longitude" : 8.7147,
                "latitude" : 50.1103
            },
            {
                "ip" : "51.89.6.40",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "104.237.151.221",
                "longitude" : -74.1697,
                "latitude" : 40.739
            },
            {
                "ip" : "82.141.39.114",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "78.46.233.214",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "144.217.4.166",
                "longitude" : -73.8736,
                "latitude" : 45.3161
            },
            {
                "ip" : "213.73.96.131",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "212.83.149.61",
                "longitude" : 2.3527,
                "latitude" : 48.8543
            },
            {
                "ip" : "193.11.114.43",
                "longitude" : 18.056,
                "latitude" : 59.3247
            },
            {
                "ip" : "62.141.38.69",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "142.4.209.31",
                "longitude" : -73.5794,
                "latitude" : 45.5063
            },
            {
                "ip" : "193.169.145.194",
                "longitude" : 25,
                "latitude" : 46
            },
            {
                "ip" : "108.28.56.36",
                "longitude" : -77.3994,
                "latitude" : 39.023
            },
            {
                "ip" : "92.170.99.62",
                "longitude" : 2.5431,
                "latitude" : 48.9193
            },
            {
                "ip" : "81.169.235.154",
                "longitude" : 13.3985,
                "latitude" : 52.5174
            },
            {
                "ip" : "75.127.15.73",
                "longitude" : -78.8781,
                "latitude" : 42.8864
            },
            {
                "ip" : "188.165.211.164",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "86.57.149.3",
                "longitude" : 27.5667,
                "latitude" : 53.9
            },
            {
                "ip" : "171.25.193.25",
                "longitude" : 18.056,
                "latitude" : 59.3247
            },
            {
                "ip" : "172.92.156.32",
                "longitude" : -122.345,
                "latitude" : 47.6144
            },
            {
                "ip" : "37.187.105.207",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "185.220.101.30",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "163.172.176.21",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "138.68.15.191",
                "longitude" : -121.975,
                "latitude" : 37.3417
            },
            {
                "ip" : "98.217.219.44",
                "longitude" : -70.8499,
                "latitude" : 42.4947
            },
            {
                "ip" : "85.17.88.177",
                "longitude" : 4.8995,
                "latitude" : 52.3824
            },
            {
                "ip" : "94.142.241.138",
                "longitude" : 4.8995,
                "latitude" : 52.3824
            },
            {
                "ip" : "108.160.176.91",
                "longitude" : -97.3134,
                "latitude" : 32.5309
            },
            {
                "ip" : "165.227.32.113",
                "longitude" : -79.3623,
                "latitude" : 43.6547
            },
            {
                "ip" : "51.254.241.51",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "37.187.21.157",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "216.218.134.12",
                "longitude" : -97.822,
                "latitude" : 37.751
            },
            {
                "ip" : "192.42.115.102",
                "longitude" : 4.8995,
                "latitude" : 52.3824
            },
            {
                "ip" : "173.199.70.185",
                "longitude" : 9.9272,
                "latitude" : 56.0399
            },
            {
                "ip" : "87.140.73.81",
                "longitude" : 9.685,
                "latitude" : 53.7494
            },
            {
                "ip" : "84.19.190.178",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "93.104.209.61",
                "longitude" : 11.6074,
                "latitude" : 48.1089
            },
            {
                "ip" : "81.17.27.138",
                "longitude" : 8.1551,
                "latitude" : 47.1449
            },
            {
                "ip" : "213.55.240.77",
                "longitude" : 6.2701,
                "latitude" : 46.4208
            },
            {
                "ip" : "85.229.166.216",
                "longitude" : 17.9479,
                "latitude" : 58.9034
            },
            {
                "ip" : "145.239.72.73",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "62.141.39.8",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "159.69.21.196",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "51.77.193.218",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "94.16.122.65",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "194.55.15.222",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "193.111.115.210",
                "longitude" : 30.5233,
                "latitude" : 50.45
            },
            {
                "ip" : "5.51.254.248",
                "longitude" : 2.3527,
                "latitude" : 48.8543
            },
            {
                "ip" : "178.79.169.193",
                "longitude" : -0.093,
                "latitude" : 51.5164
            },
            {
                "ip" : "195.251.252.226",
                "longitude" : 23.7353,
                "latitude" : 37.9842
            },
            {
                "ip" : "213.239.214.13",
                "longitude" : 11.0683,
                "latitude" : 49.4475
            },
            {
                "ip" : "46.166.182.20",
                "longitude" : 4.8995,
                "latitude" : 52.3824
            },
            {
                "ip" : "91.121.75.85",
                "longitude" : 2.6667,
                "latitude" : 50.4833
            },
            {
                "ip" : "104.57.231.26",
                "longitude" : -122.074,
                "latitude" : 37.7045
            },
            {
                "ip" : "104.244.79.222",
                "longitude" : 6.0961,
                "latitude" : 49.7867
            },
            {
                "ip" : "162.247.72.199",
                "longitude" : -74.0018,
                "latitude" : 40.6521
            },
            {
                "ip" : "185.163.45.247",
                "longitude" : 28.8573,
                "latitude" : 47.0052
            },
            {
                "ip" : "188.68.45.72",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "185.246.152.22",
                "longitude" : 4.8995,
                "latitude" : 52.3824
            },
            {
                "ip" : "199.249.230.121",
                "longitude" : -97.822,
                "latitude" : 37.751
            },
            {
                "ip" : "37.252.188.140",
                "longitude" : 16.3667,
                "latitude" : 48.2
            },
            {
                "ip" : "178.175.135.102",
                "longitude" : 28.8573,
                "latitude" : 47.0052
            },
            {
                "ip" : "51.15.60.93",
                "longitude" : 4.6419,
                "latitude" : 52.3615
            },
            {
                "ip" : "185.15.246.243",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "155.138.223.47",
                "longitude" : -84.4455,
                "latitude" : 33.7838
            },
            {
                "ip" : "185.220.101.29",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "213.227.133.129",
                "longitude" : 4.8995,
                "latitude" : 52.3824
            },
            {
                "ip" : "173.198.243.126",
                "longitude" : -73.7527,
                "latitude" : 42.747
            },
            {
                "ip" : "35.183.27.143",
                "longitude" : -73.5848,
                "latitude" : 45.4995
            },
            {
                "ip" : "51.15.74.130",
                "longitude" : 4.8995,
                "latitude" : 52.3824
            },
            {
                "ip" : "209.126.103.140",
                "longitude" : -90.1985,
                "latitude" : 38.6364
            },
            {
                "ip" : "185.132.133.30",
                "longitude" : 4.9167,
                "latitude" : 52.35
            },
            {
                "ip" : "89.25.33.13",
                "longitude" : 23.3333,
                "latitude" : 42.7
            },
            {
                "ip" : "51.68.137.123",
                "longitude" : 21.0362,
                "latitude" : 52.2394
            },
            {
                "ip" : "187.178.75.109",
                "longitude" : -99.1438,
                "latitude" : 19.4357
            },
            {
                "ip" : "108.62.211.205",
                "longitude" : -73.9975,
                "latitude" : 40.7308
            },
            {
                "ip" : "46.165.245.154",
                "longitude" : 9.491,
                "latitude" : 51.2993
            },
            {
                "ip" : "209.148.46.83",
                "longitude" : -97.822,
                "latitude" : 37.751
            },
            {
                "ip" : "27.122.59.86",
                "longitude" : 103.855,
                "latitude" : 1.2929
            },
            {
                "ip" : "185.96.88.29",
                "longitude" : 12.0564,
                "latitude" : 55.7123
            },
            {
                "ip" : "195.123.228.107",
                "longitude" : 23.3175,
                "latitude" : 42.683
            },
            {
                "ip" : "178.32.59.160",
                "longitude" : -0.066,
                "latitude" : 51.5026
            },
            {
                "ip" : "81.17.27.140",
                "longitude" : 8.1551,
                "latitude" : 47.1449
            },
            {
                "ip" : "79.134.235.243",
                "longitude" : 8.1551,
                "latitude" : 47.1449
            },
            {
                "ip" : "5.135.199.13",
                "longitude" : 2.3387,
                "latitude" : 48.8582
            },
            {
                "ip" : "185.163.45.29",
                "longitude" : 28.8573,
                "latitude" : 47.0052
            },
            {
                "ip" : "83.136.107.143",
                "longitude" : 9.1889,
                "latitude" : 45.4707
            },
            {
                "ip" : "199.249.230.83",
                "longitude" : -97.822,
                "latitude" : 37.751
            },
            {
                "ip" : "156.67.221.48",
                "longitude" : 103.8,
                "latitude" : 1.3667
            }
        ]
    }); // end am4core.ready()
</script>

<!-- HTML -->
<div id="chartdiv"></div>
amCharts


<br><br><br>

<div id="histroyBox"></div>
<script type="text/javascript">
    //前台分页的样子
    data = ${res}
        $('#histroyBox').CJJTable({
            'title':["ip","国家","国家代码","城市","经度","维度"],//thead中的标题 必填
            'body':["ip","country","countryCode","city","longitude","latitude"],//tbody td 取值的字段 必填
            'display':[1,1,1,1,1,1],//隐藏域，1显示，2隐藏 必填
            'pageNUmber':30,//每页显示的条数 选填
            'pageLength':data.length,//选填
            'url':data,//数据源 必填
            dbTrclick:function(e){//双击tr事件
                alert(e.find('.time').html())
            }
        });
    //后台分页的样子
    /*$('#histroyBox').CJJTable({
        'title':["装点","卸点","运输货物","下单日期"],//thead中的标题 必填
        'body':["contactName","contactMobliePhone","carrierName","taskNum","taskCustomerExpectPrice","taskCustomerBudgetFreight"],//tbody td 取值的字段 必填
        'display':[1,1,1,1,2,2],//隐藏域，1显示，2隐藏 必填
        'pageJson':{
            "taskId":528710,
            "pageSize":100,//ajax请求参数中的每页展示数量 选填
            "token":"yJUmunFeG3REqisYAmCfeA"
        },
        'url':'api/quoted/quotedList',//数据源 必填
        dbTrclick:function(that){ //双击tr事件
            alert(that.find('.contactName').html())
        }
    });*/
</script>
</body>
</html>
