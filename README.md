GameSnag - http://gamesnag.duckdns.org/

GameSnag is a Java Spring Boot application with a HTML/CSS/JS frontend that scrapes game sales from multiple websites and consolidates them into a single, easy-to-browse platform. It provides a unified view of discounted games across different stores, making it easy for users to find the best deals.

Features
- Scrapes game sale data from multiple gaming websites.
- Consolidates all sales into one unified frontend.
- Responsive HTML/CSS/JS interface for browsing and searching games.
- Backend built with Java Spring Boot for fast and reliable data aggregation.
- REST API to provide game sale data in JSON format, enabling integration with other apps, scripts, or services.

Technology Stack
- Backend: Java, Spring Boot
- Frontend: HTML, CSS, JavaScript
- Data fetching: Web scraping with Java libraries (e.g., Jsoup, Playwright for Java)
- Server: Runs on an Oracle Cloud VM with Linux OS. The frontend is served by Nginx on the VM and the backend is run in the VM.
- Logging: Output redirected to output.log when run in background

Prerequisites
- Java JDK 25
- Maven
- Linux or Windows environment
- Internet access for scraping target websites

Future Improvements
- Expand scraping to more stores and regions.
- Expand the API to allow more specific queries, such as filtering by platform, price range, release date, or discount percentage.

API

The backend exposes a REST API that provides game sale data in JSON format. You can use it to fetch games programmatically, filter by platform, store, or price, or integrate with other applications.

Base URL
- http://129.153.133.78:8080/api/v1/game/

API Endpoints
- GET base_url + allgames - Returns all games on sale currently
- GET base_url + ?title=game_title_here - Returns game data for a specific game title
