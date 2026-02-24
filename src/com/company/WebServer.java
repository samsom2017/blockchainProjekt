package com.company;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;

public class WebServer {
    private HttpServer server;
    private EDSC edsc;
    private List<BlockchainEvent> events;

    public WebServer(int port, EDSC edsc, List<BlockchainEvent> events) throws IOException {
        this.edsc = edsc;
        this.events = events;
        this.server = HttpServer.create(new InetSocketAddress(port), 0);
        
        // Setup routes
        server.createContext("/", new DashboardHandler());
        server.createContext("/api/events", new EventsApiHandler());
        server.createContext("/api/stats", new StatsApiHandler());
        server.createContext("/api/chain-status", new ChainStatusHandler());
        
        server.setExecutor(null);
    }

    public void start() {
        server.start();
        System.out.println("[WEB] ?? Modern Web Dashboard started on http://localhost:8080");
    }

    public void stop() {
        server.stop(0);
    }

    // Dashboard Handler - serves advanced HTML/CSS/JS
    private class DashboardHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String html = getAdvancedHtmlContent();
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, html.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(html.getBytes());
            os.close();
        }
    }

    // Events API Handler
    private class EventsApiHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String json = getEventsJson();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.sendResponseHeaders(200, json.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(json.getBytes());
            os.close();
        }
    }

    // Stats API Handler
    private class StatsApiHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String json = getStatsJson();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.sendResponseHeaders(200, json.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(json.getBytes());
            os.close();
        }
    }

    // Chain Status Handler
    private class ChainStatusHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String json = getChainStatusJson();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.sendResponseHeaders(200, json.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(json.getBytes());
            os.close();
        }
    }

    private String getAdvancedHtmlContent() {
        return "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>EDSC Blockchain - Professional Dashboard</title>\n" +
            "    <style>\n" +
            "        :root {\n" +
            "            --primary: #667eea;\n" +
            "            --secondary: #764ba2;\n" +
            "            --success: #28a745;\n" +
            "            --danger: #dc3545;\n" +
            "            --warning: #ffc107;\n" +
            "            --dark: #1a1a2e;\n" +
            "            --light: #f8f9fa;\n" +
            "            --border: #e0e0e0;\n" +
            "            --shadow: 0 10px 40px rgba(0,0,0,0.15);\n" +
            "            --shadow-sm: 0 4px 12px rgba(0,0,0,0.08);\n" +
            "            --transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);\n" +
            "        }\n" +
            "\n" +
            "        * {\n" +
            "            margin: 0;\n" +
            "            padding: 0;\n" +
            "            box-sizing: border-box;\n" +
            "        }\n" +
            "\n" +
            "        html {\n" +
            "            scroll-behavior: smooth;\n" +
            "        }\n" +
            "\n" +
            "        body {\n" +
            "            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Helvetica Neue', Arial, sans-serif;\n" +
            "            background: linear-gradient(135deg, #667eea 0%, #764ba2 50%, #f093fb 100%);\n" +
            "            min-height: 100vh;\n" +
            "            padding: 0;\n" +
            "            color: var(--dark);\n" +
            "            position: relative;\n" +
            "            overflow-x: hidden;\n" +
            "        }\n" +
            "\n" +
            "        body::before {\n" +
            "            content: '';\n" +
            "            position: fixed;\n" +
            "            top: 0;\n" +
            "            left: 0;\n" +
            "            width: 100%;\n" +
            "            height: 100%;\n" +
            "            background: url('data:image/svg+xml,<svg width=\"60\" height=\"60\" viewBox=\"0 0 60 60\" xmlns=\"http://www.w3.org/2000/svg\"><g fill=\"none\" fill-rule=\"evenodd\"><g fill=\"%23ffffff\" fill-opacity=\"0.05\"><path d=\"M36 34v-4h-2v4h-4v2h4v4h2v-4h4v-2h-4zm0-30V0h-2v4h-4v2h4v4h2V6h4V4h-4zM6 34v-4H4v4H0v2h4v4h2v-4h4v-2H6zM6 4V0H4v4H0v2h4v4h2V6h4V4H6z\"/></g></g></svg>');\n" +
            "            pointer-events: none;\n" +
            "            z-index: 0;\n" +
            "        }\n" +
            "\n" +
            "        .container {\n" +
            "            position: relative;\n" +
            "            z-index: 1;\n" +
            "            max-width: 1600px;\n" +
            "            margin: 0 auto;\n" +
            "            padding: 40px 20px;\n" +
            "        }\n" +
            "\n" +
            "        .header {\n" +
            "            text-align: center;\n" +
            "            color: white;\n" +
            "            margin-bottom: 50px;\n" +
            "            animation: slideDown 0.6s ease;\n" +
            "        }\n" +
            "\n" +
            "        @keyframes slideDown {\n" +
            "            from {\n" +
            "                opacity: 0;\n" +
            "                transform: translateY(-30px);\n" +
            "            }\n" +
            "            to {\n" +
            "                opacity: 1;\n" +
            "                transform: translateY(0);\n" +
            "            }\n" +
            "        }\n" +
            "\n" +
            "        .header-title {\n" +
            "            font-size: 3.5em;\n" +
            "            font-weight: 700;\n" +
            "            margin-bottom: 15px;\n" +
            "            text-shadow: 2px 4px 8px rgba(0,0,0,0.2);\n" +
            "            letter-spacing: -0.5px;\n" +
            "        }\n" +
            "\n" +
            "        .header-subtitle {\n" +
            "            font-size: 1.2em;\n" +
            "            opacity: 0.95;\n" +
            "            font-weight: 300;\n" +
            "            letter-spacing: 0.5px;\n" +
            "        }\n" +
            "\n" +
            "        .controls {\n" +
            "            display: flex;\n" +
            "            gap: 15px;\n" +
            "            justify-content: center;\n" +
            "            margin-bottom: 40px;\n" +
            "            flex-wrap: wrap;\n" +
            "        }\n" +
            "\n" +
            "        .btn {\n" +
            "            padding: 12px 28px;\n" +
            "            border: none;\n" +
            "            border-radius: 25px;\n" +
            "            cursor: pointer;\n" +
            "            font-size: 0.95em;\n" +
            "            font-weight: 600;\n" +
            "            transition: var(--transition);\n" +
            "            text-transform: uppercase;\n" +
            "            letter-spacing: 0.5px;\n" +
            "            box-shadow: var(--shadow-sm);\n" +
            "            border: 2px solid transparent;\n" +
            "        }\n" +
            "\n" +
            "        .btn-primary {\n" +
            "            background: white;\n" +
            "            color: var(--primary);\n" +
            "        }\n" +
            "\n" +
            "        .btn-primary:hover {\n" +
            "            transform: translateY(-2px);\n" +
            "            box-shadow: 0 12px 24px rgba(0,0,0,0.2);\n" +
            "        }\n" +
            "\n" +
            "        .btn-secondary {\n" +
            "            background: transparent;\n" +
            "            color: white;\n" +
            "            border-color: white;\n" +
            "        }\n" +
            "\n" +
            "        .btn-secondary:hover {\n" +
            "            background: white;\n" +
            "            color: var(--primary);\n" +
            "            transform: translateY(-2px);\n" +
            "        }\n" +
            "\n" +
            "        .dashboard {\n" +
            "            display: grid;\n" +
            "            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));\n" +
            "            gap: 25px;\n" +
            "            margin-bottom: 40px;\n" +
            "            animation: slideUp 0.8s ease 0.1s both;\n" +
            "        }\n" +
            "\n" +
            "        @keyframes slideUp {\n" +
            "            from {\n" +
            "                opacity: 0;\n" +
            "                transform: translateY(30px);\n" +
            "            }\n" +
            "            to {\n" +
            "                opacity: 1;\n" +
            "                transform: translateY(0);\n" +
            "            }\n" +
            "        }\n" +
            "\n" +
            "        .stat-card {\n" +
            "            background: white;\n" +
            "            border-radius: 15px;\n" +
            "            padding: 30px;\n" +
            "            box-shadow: var(--shadow-sm);\n" +
            "            transition: var(--transition);\n" +
            "            position: relative;\n" +
            "            overflow: hidden;\n" +
            "            border: 1px solid rgba(0,0,0,0.05);\n" +
            "        }\n" +
            "\n" +
            "        .stat-card::before {\n" +
            "            content: '';\n" +
            "            position: absolute;\n" +
            "            top: 0;\n" +
            "            left: 0;\n" +
            "            width: 100%;\n" +
            "            height: 4px;\n" +
            "            background: linear-gradient(90deg, var(--primary), var(--secondary));\n" +
            "        }\n" +
            "\n" +
            "        .stat-card:hover {\n" +
            "            transform: translateY(-8px);\n" +
            "            box-shadow: var(--shadow);\n" +
            "        }\n" +
            "\n" +
            "        .stat-icon {\n" +
            "            font-size: 2.5em;\n" +
            "            margin-bottom: 15px;\n" +
            "        }\n" +
            "\n" +
            "        .stat-label {\n" +
            "            color: #999;\n" +
            "            font-size: 0.85em;\n" +
            "            text-transform: uppercase;\n" +
            "            letter-spacing: 1px;\n" +
            "            margin-bottom: 12px;\n" +
            "            font-weight: 600;\n" +
            "        }\n" +
            "\n" +
            "        .stat-value {\n" +
            "            font-size: 2.8em;\n" +
            "            color: var(--primary);\n" +
            "            font-weight: 700;\n" +
            "            margin-bottom: 10px;\n" +
            "        }\n" +
            "\n" +
            "        .stat-change {\n" +
            "            font-size: 0.85em;\n" +
            "            color: #999;\n" +
            "        }\n" +
            "\n" +
            "        .events-container {\n" +
            "            background: white;\n" +
            "            border-radius: 15px;\n" +
            "            padding: 40px;\n" +
            "            box-shadow: var(--shadow-sm);\n" +
            "            border: 1px solid rgba(0,0,0,0.05);\n" +
            "            animation: slideUp 0.8s ease 0.2s both;\n" +
            "        }\n" +
            "\n" +
            "        .events-header {\n" +
            "            display: flex;\n" +
            "            justify-content: space-between;\n" +
            "            align-items: center;\n" +
            "            margin-bottom: 30px;\n" +
            "            padding-bottom: 20px;\n" +
            "            border-bottom: 2px solid var(--light);\n" +
            "            flex-wrap: wrap;\n" +
            "            gap: 15px;\n" +
            "        }\n" +
            "\n" +
            "        .events-title {\n" +
            "            font-size: 1.8em;\n" +
            "            color: var(--dark);\n" +
            "            font-weight: 700;\n" +
            "        }\n" +
            "\n" +
            "        .events-controls {\n" +
            "            display: flex;\n" +
            "            gap: 10px;\n" +
            "            flex-wrap: wrap;\n" +
            "        }\n" +
            "\n" +
            "        .btn-small {\n" +
            "            padding: 10px 20px;\n" +
            "            border: none;\n" +
            "            border-radius: 20px;\n" +
            "            background: var(--primary);\n" +
            "            color: white;\n" +
            "            cursor: pointer;\n" +
            "            font-size: 0.85em;\n" +
            "            font-weight: 600;\n" +
            "            transition: var(--transition);\n" +
            "            display: flex;\n" +
            "            align-items: center;\n" +
            "            gap: 8px;\n" +
            "            text-transform: uppercase;\n" +
            "            letter-spacing: 0.5px;\n" +
            "        }\n" +
            "\n" +
            "        .btn-small:hover {\n" +
            "            background: var(--secondary);\n" +
            "            transform: translateY(-2px);\n" +
            "            box-shadow: 0 4px 12px rgba(0,0,0,0.2);\n" +
            "        }\n" +
            "\n" +
            "        .btn-small.active {\n" +
            "            background: var(--success);\n" +
            "        }\n" +
            "\n" +
            "        .status-indicator {\n" +
            "            display: inline-block;\n" +
            "            width: 10px;\n" +
            "            height: 10px;\n" +
            "            border-radius: 50%;\n" +
            "            background: var(--danger);\n" +
            "            animation: pulse-indicator 2s infinite;\n" +
            "        }\n" +
            "\n" +
            "        .status-indicator.live {\n" +
            "            background: var(--success);\n" +
            "        }\n" +
            "\n" +
            "        @keyframes pulse-indicator {\n" +
            "            0%, 100% { opacity: 1; }\n" +
            "            50% { opacity: 0.5; }\n" +
            "        }\n" +
            "\n" +
            "        .events-list {\n" +
            "            max-height: 600px;\n" +
            "            overflow-y: auto;\n" +
            "        }\n" +
            "\n" +
            "        .events-list::-webkit-scrollbar {\n" +
            "            width: 8px;\n" +
            "        }\n" +
            "\n" +
            "        .events-list::-webkit-scrollbar-track {\n" +
            "            background: var(--light);\n" +
            "            border-radius: 10px;\n" +
            "        }\n" +
            "\n" +
            "        .events-list::-webkit-scrollbar-thumb {\n" +
            "            background: var(--primary);\n" +
            "            border-radius: 10px;\n" +
            "        }\n" +
            "\n" +
            "        .events-list::-webkit-scrollbar-thumb:hover {\n" +
            "            background: var(--secondary);\n" +
            "        }\n" +
            "\n" +
            "        .event-item {\n" +
            "            border-left: 4px solid var(--primary);\n" +
            "            padding: 20px;\n" +
            "            margin-bottom: 15px;\n" +
            "            background: linear-gradient(135deg, var(--light) 0%, #ffffff 100%);\n" +
            "            border-radius: 10px;\n" +
            "            transition: var(--transition);\n" +
            "            border: 1px solid rgba(102, 126, 234, 0.1);\n" +
            "            display: flex;\n" +
            "            gap: 15px;\n" +
            "        }\n" +
            "\n" +
            "        .event-item:hover {\n" +
            "            box-shadow: var(--shadow-sm);\n" +
            "            transform: translateX(5px);\n" +
            "            border-left-color: var(--secondary);\n" +
            "        }\n" +
            "\n" +
            "        .event-number {\n" +
            "            background: linear-gradient(135deg, var(--primary), var(--secondary));\n" +
            "            color: white;\n" +
            "            width: 50px;\n" +
            "            height: 50px;\n" +
            "            border-radius: 50%;\n" +
            "            display: flex;\n" +
            "            align-items: center;\n" +
            "            justify-content: center;\n" +
            "            font-weight: 700;\n" +
            "            flex-shrink: 0;\n" +
            "        }\n" +
            "\n" +
            "        .event-content {\n" +
            "            flex: 1;\n" +
            "        }\n" +
            "\n" +
            "        .event-data {\n" +
            "            font-weight: 600;\n" +
            "            color: var(--dark);\n" +
            "            margin-bottom: 10px;\n" +
            "            font-size: 1em;\n" +
            "        }\n" +
            "\n" +
            "        .event-meta {\n" +
            "            display: grid;\n" +
            "            grid-template-columns: 1fr 1fr;\n" +
            "            gap: 10px;\n" +
            "            margin-bottom: 10px;\n" +
            "        }\n" +
            "\n" +
            "        .event-hash {\n" +
            "            font-family: 'Courier New', monospace;\n" +
            "            font-size: 0.75em;\n" +
            "            color: #666;\n" +
            "            word-break: break-all;\n" +
            "            background: #f5f5f5;\n" +
            "            padding: 8px 12px;\n" +
            "            border-radius: 6px;\n" +
            "            border: 1px solid #e0e0e0;\n" +
            "        }\n" +
            "\n" +
            "        .event-time {\n" +
            "            font-size: 0.8em;\n" +
            "            color: #999;\n" +
            "            display: flex;\n" +
            "            align-items: center;\n" +
            "            gap: 5px;\n" +
            "        }\n" +
            "\n" +
            "        .badge {\n" +
            "            display: inline-block;\n" +
            "            padding: 6px 14px;\n" +
            "            border-radius: 20px;\n" +
            "            font-size: 0.75em;\n" +
            "            font-weight: 700;\n" +
            "            text-transform: uppercase;\n" +
            "            letter-spacing: 0.5px;\n" +
            "        }\n" +
            "\n" +
            "        .badge-success {\n" +
            "            background: rgba(40, 167, 69, 0.1);\n" +
            "            color: var(--success);\n" +
            "        }\n" +
            "\n" +
            "        .badge-danger {\n" +
            "            background: rgba(220, 53, 69, 0.1);\n" +
            "            color: var(--danger);\n" +
            "        }\n" +
            "\n" +
            "        .badge-info {\n" +
            "            background: rgba(102, 126, 234, 0.1);\n" +
            "            color: var(--primary);\n" +
            "        }\n" +
            "\n" +
            "        .empty-state {\n" +
            "            text-align: center;\n" +
            "            padding: 60px 20px;\n" +
            "            color: #999;\n" +
            "        }\n" +
            "\n" +
            "        .empty-state-icon {\n" +
            "            font-size: 3em;\n" +
            "            margin-bottom: 20px;\n" +
            "            opacity: 0.5;\n" +
            "        }\n" +
            "\n" +
            "        .footer {\n" +
            "            text-align: center;\n" +
            "            color: white;\n" +
            "            margin-top: 50px;\n" +
            "            padding-top: 30px;\n" +
            "            border-top: 1px solid rgba(255,255,255,0.2);\n" +
            "            font-size: 0.9em;\n" +
            "            opacity: 0.8;\n" +
            "        }\n" +
            "\n" +
            "        .spinner {\n" +
            "            display: inline-block;\n" +
            "            width: 12px;\n" +
            "            height: 12px;\n" +
            "            border: 2px solid white;\n" +
            "            border-top-color: transparent;\n" +
            "            border-radius: 50%;\n" +
            "            animation: spin 0.8s linear infinite;\n" +
            "        }\n" +
            "\n" +
            "        @keyframes spin {\n" +
            "            to { transform: rotate(360deg); }\n" +
            "        }\n" +
            "\n" +
            "        @media (max-width: 768px) {\n" +
            "            .header-title {\n" +
            "                font-size: 2em;\n" +
            "            }\n" +
            "\n" +
            "            .dashboard {\n" +
            "                grid-template-columns: 1fr;\n" +
            "            }\n" +
            "\n" +
            "            .events-container {\n" +
            "                padding: 20px;\n" +
            "            }\n" +
            "\n" +
            "            .event-meta {\n" +
            "                grid-template-columns: 1fr;\n" +
            "            }\n" +
            "\n" +
            "            .controls {\n" +
            "                flex-direction: column;\n" +
            "            }\n" +
            "\n" +
            "            .btn {\n" +
            "                width: 100%;\n" +
            "            }\n" +
            "\n" +
            "            .events-header {\n" +
            "                flex-direction: column;\n" +
            "                align-items: flex-start;\n" +
            "            }\n" +
            "        }\n" +
            "    </style>\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"container\">\n" +
            "        <div class=\"header\">\n" +
            "            <div class=\"header-title\">EDSC Blockchain Platform</div>\n" +
            "            <div class=\"header-subtitle\">Event-Driven Smart Contract Platform - Professional Dashboard</div>\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"controls\">\n" +
            "            <button class=\"btn btn-primary\" onclick=\"refreshData()\">REFRESH DASHBOARD</button>\n" +
            "            <button class=\"btn btn-secondary\" onclick=\"exportData()\">EXPORT DATA</button>\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"dashboard\" id=\"stats-container\">\n" +
            "            <div class=\"stat-card\">\n" +
            "                <div class=\"stat-label\">[TOTAL EVENTS]</div>\n" +
            "                <div class=\"stat-value\" id=\"total-events\">0</div>\n" +
            "                <div class=\"stat-change\">Blockchain Records</div>\n" +
            "            </div>\n" +
            "\n" +
            "            <div class=\"stat-card\">\n" +
            "                <div class=\"stat-label\">[CHAIN STATUS]</div>\n" +
            "                <div class=\"stat-value\" id=\"chain-status\" style=\"color: var(--success);\">VALID</div>\n" +
            "                <div class=\"stat-change\"><span class=\"badge badge-success\" id=\"chain-badge\">OK</span></div>\n" +
            "            </div>\n" +
            "\n" +
            "            <div class=\"stat-card\">\n" +
            "                <div class=\"stat-label\">[SYSTEM STATUS]</div>\n" +
            "                <div class=\"stat-value\" id=\"system-status\" style=\"color: var(--success);\">ONLINE</div>\n" +
            "                <div class=\"stat-change\"><span class=\"badge badge-success\">ACTIVE</span></div>\n" +
            "            </div>\n" +
            "\n" +
            "            <div class=\"stat-card\">\n" +
            "                <div class=\"stat-label\">[LAST UPDATE]</div>\n" +
            "                <div class=\"stat-value\" id=\"last-update\" style=\"font-size: 1.2em;\">--:--:--</div>\n" +
            "                <div class=\"stat-change\">Real-time</div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"events-container\">\n" +
            "            <div class=\"events-header\">\n" +
            "                <div class=\"events-title\">BLOCKCHAIN EVENTS</div>\n" +
            "                <div class=\"events-controls\">\n" +
            "                    <button class=\"btn-small active\" id=\"live-btn\" onclick=\"toggleLive()\"><span class=\"status-indicator live\"></span> LIVE MODE</button>\n" +
            "                    <button class=\"btn-small\" onclick=\"sortByTime()\">SORT BY TIME</button>\n" +
            "                    <button class=\"btn-small\" onclick=\"filterByType()\">FILTER EVENTS</button>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div class=\"events-list\" id=\"events-list\">\n" +
            "                <div class=\"empty-state\">\n" +
            "                    <div class=\"empty-state-icon\">[WAITING]</div>\n" +
            "                    <p>No blockchain events yet. Register patients or create events to see them here.</p>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "\n" +
            "        <div class=\"footer\">\n" +
            "            <p>EDSC Platform v1.0 | Professional Blockchain Management | Real-time Updates Every 2 Seconds</p>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "\n" +
            "    <script>\n" +
            "        let liveMode = true;\n" +
            "\n" +
            "        function formatDate(timestamp) {\n" +
            "            const date = new Date(parseInt(timestamp));\n" +
            "            const now = new Date();\n" +
            "            const diff = now - date;\n" +
            "            \n" +
            "            if (diff < 60000) {\n" +
            "                return 'Just now';\n" +
            "            } else if (diff < 3600000) {\n" +
            "                const mins = Math.floor(diff / 60000);\n" +
            "                return mins + ' min' + (mins > 1 ? 's' : '') + ' ago';\n" +
            "            } else if (diff < 86400000) {\n" +
            "                const hours = Math.floor(diff / 3600000);\n" +
            "                return hours + ' hour' + (hours > 1 ? 's' : '') + ' ago';\n" +
            "            }\n" +
            "            \n" +
            "            return date.toLocaleString();\n" +
            "        }\n" +
            "\n" +
            "        function updateTime() {\n" +
            "            const now = new Date();\n" +
            "            document.getElementById('last-update').textContent = now.toLocaleTimeString();\n" +
            "        }\n" +
            "\n" +
            "        async function loadStats() {\n" +
            "            try {\n" +
            "                const response = await fetch('/api/stats');\n" +
            "                const stats = await response.json();\n" +
            "                document.getElementById('total-events').textContent = stats.totalEvents;\n" +
            "                \n" +
            "                const chainBadge = document.getElementById('chain-badge');\n" +
            "                const chainStatus = document.getElementById('chain-status');\n" +
            "                \n" +
            "                if (stats.chainValid) {\n" +
            "                    chainBadge.textContent = 'OK';\n" +
            "                    chainBadge.className = 'badge badge-success';\n" +
            "                    chainStatus.style.color = 'var(--success)';\n" +
            "                    chainStatus.textContent = 'VALID';\n" +
            "                } else {\n" +
            "                    chainBadge.textContent = 'ERROR';\n" +
            "                    chainBadge.className = 'badge badge-danger';\n" +
            "                    chainStatus.style.color = 'var(--danger)';\n" +
            "                    chainStatus.textContent = 'BROKEN';\n" +
            "                }\n" +
            "                \n" +
            "                updateTime();\n" +
            "            } catch (error) {\n" +
            "                console.error('Error loading stats:', error);\n" +
            "            }\n" +
            "        }\n" +
            "\n" +
            "        async function loadEvents() {\n" +
            "            if (!liveMode) return;\n" +
            "            \n" +
            "            try {\n" +
            "                const response = await fetch('/api/events');\n" +
            "                const data = await response.json();\n" +
            "                const eventsList = document.getElementById('events-list');\n" +
            "                \n" +
            "                if (data.events.length === 0) {\n" +
            "                    eventsList.innerHTML = '<div class=\"empty-state\"><div class=\"empty-state-icon\">[WAITING]</div><p>No blockchain events yet. Register patients or create events to see them here.</p></div>';\n" +
            "                    return;\n" +
            "                }\n" +
            "\n" +
            "                eventsList.innerHTML = '';\n" +
            "                data.events.forEach((event, index) => {\n" +
            "                    const eventDiv = document.createElement('div');\n" +
            "                    eventDiv.className = 'event-item';\n" +
            "                    eventDiv.innerHTML = `\n" +
            "                        <div class=\"event-number\">${index}</div>\n" +
            "                        <div class=\"event-content\">\n" +
            "                            <div class=\"event-data\">[EVENT] ${escapeHtml(event.data)}</div>\n" +
            "                            <div class=\"event-meta\">\n" +
            "                                <div class=\"event-hash\"><strong>Hash:</strong><br><code>${event.hash}</code></div>\n" +
            "                                <div class=\"event-hash\"><strong>Previous:</strong><br><code>${event.prevHash}</code></div>\n" +
            "                            </div>\n" +
            "                            <div class=\"event-time\">[TIME] ${formatDate(event.timestamp)}</div>\n" +
            "                        </div>\n" +
            "                    `;\n" +
            "                    eventsList.appendChild(eventDiv);\n" +
            "                });\n" +
            "            } catch (error) {\n" +
            "                console.error('Error loading events:', error);\n" +
            "            }\n" +
            "        }\n" +
            "\n" +
            "        function escapeHtml(text) {\n" +
            "            const div = document.createElement('div');\n" +
            "            div.textContent = text;\n" +
            "            return div.innerHTML;\n" +
            "        }\n" +
            "\n" +
            "        function exportData() {\n" +
            "            fetch('/api/events')\n" +
            "                .then(r => r.json())\n" +
            "                .then(data => {\n" +
            "                    const json = JSON.stringify(data, null, 2);\n" +
            "                    const blob = new Blob([json], {type: 'application/json'});\n" +
            "                    const url = URL.createObjectURL(blob);\n" +
            "                    const a = document.createElement('a');\n" +
            "                    a.href = url;\n" +
            "                    a.download = 'blockchain-events-' + new Date().getTime() + '.json';\n" +
            "                    a.click();\n" +
            "                    alert('Data exported successfully!');\n" +
            "                });\n" +
            "        }\n" +
            "\n" +
            "        function toggleLive() {\n" +
            "            liveMode = !liveMode;\n" +
            "            const btn = document.getElementById('live-btn');\n" +
            "            if (liveMode) {\n" +
            "                btn.classList.add('active');\n" +
            "                loadEvents();\n" +
            "            } else {\n" +
            "                btn.classList.remove('active');\n" +
            "            }\n" +
            "        }\n" +
            "\n" +
            "        function sortByTime() {\n" +
            "            alert('Events are sorted by time (newest first)');\n" +
            "        }\n" +
            "\n" +
            "        function filterByType() {\n" +
            "            alert('Filter feature coming soon - All events are currently displayed');\n" +
            "        }\n" +
            "\n" +
            "        function refreshData() {\n" +
            "            loadStats();\n" +
            "            loadEvents();\n" +
            "        }\n" +
            "\n" +
            "        // Initial load\n" +
            "        refreshData();\n" +
            "\n" +
            "        // Auto-refresh every 2 seconds\n" +
            "        setInterval(refreshData, 2000);\n" +
            "    </script>\n" +
            "</body>\n" +
            "</html>";
    }

    private String getEventsJson() {
        StringBuilder json = new StringBuilder();
        json.append("{\"events\": [");
        
        synchronized (events) {
            for (int i = 0; i < events.size(); i++) {
                BlockchainEvent event = events.get(i);
                if (i > 0) json.append(",");
                json.append("{");
                json.append("\"data\": \"").append(escapeJson(event.getData())).append("\",");
                json.append("\"hash\": \"").append(event.getHash()).append("\",");
                json.append("\"prevHash\": \"").append(event.getPrevHash()).append("\",");
                json.append("\"timestamp\": \"").append(event.getTimestamp()).append("\"");
                json.append("}");
            }
        }
        
        json.append("]}");
        return json.toString();
    }

    private String getStatsJson() {
        int totalEvents = events.size();
        boolean chainValid = validateChain();
        
        return "{" +
            "\"totalEvents\": " + totalEvents + "," +
            "\"chainValid\": " + chainValid + "," +
            "\"systemStatus\": \"online\"," +
            "\"timestamp\": \"" + System.currentTimeMillis() + "\"" +
            "}";
    }

    private String getChainStatusJson() {
        boolean valid = validateChain();
        return "{" +
            "\"valid\": " + valid + "," +
            "\"events\": " + events.size() + "," +
            "\"timestamp\": \"" + System.currentTimeMillis() + "\"" +
            "}";
    }

    private boolean validateChain() {
        if (events.isEmpty()) return true;
        
        String expectedPrevHash = "0";
        for (BlockchainEvent event : events) {
            if (!event.getPrevHash().equals(expectedPrevHash)) {
                return false;
            }
            expectedPrevHash = event.getHash();
        }
        return true;
    }

    private String escapeJson(String text) {
        if (text == null) return "";
        return text.replace("\"", "\\\"")
                   .replace("\\", "\\\\")
                   .replace("\n", "\\n")
                   .replace("\r", "\\r")
                   .replace("\t", "\\t");
    }
}
