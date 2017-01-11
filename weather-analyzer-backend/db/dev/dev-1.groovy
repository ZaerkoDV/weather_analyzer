databaseChangeLog(logicalFilePath: 'dev-1') {
    changeSet id: '1', author: 'author', {
        insert tableName: 'weather_web_site', {
            column name: 'id', value: 1
            column name: 'name', value: 'gismeteo.by'
            column name: 'full_url', value: 'https://www.gismeteo.by/weather-grodno-4243/weekly/'
            column name: 'last_success_connected', value: 1468330053
            column name: 'reating_position', value: 1
        }

        insert tableName: 'weather_web_site', {
            column name: 'id', value: 2
            column name: 'name', value: 'tut.by'
            column name: 'full_url', value: 'http://pogoda.tut.by/'
            column name: 'last_success_connected', value: 1468330053
            column name: 'reating_position', value: 2
        }

        insert tableName: 'weather_web_site', {
            column name: 'id', value: 3
            column name: 'name', value: 'pogoda.blr.cc'
            column name: 'full_url', value: 'http://pogoda.blr.cc/belarus/soligorsk/7-dney/'
            column name: 'last_success_connected', value: 1468330053
            column name: 'reating_position', value: 3
        }

        rollback {
            delete tableName: 'weather_web_site', where: 'id in (1,2,3)'
        }
    }

    changeSet id: '2', author: 'author', {
        insert tableName: 'weather_data', {
            column name: 'id', value: 1
            column name: 'created_at_timestamp', value: 1288323623006
            column name: 'web_site_forecast_temperature', value: 1
            column name: 'web_site_real_temperature', value: 2
            column name: 'error_temperature', value: 1
            column name: 'error_sign', value: 0
            column name: 'weather_web_site_id', value: 1
        }

        insert tableName: 'weather_data', {
            column name: 'id', value: 2
            column name: 'created_at_timestamp', value: 1288323623006
            column name: 'web_site_forecast_temperature', value: 2
            column name: 'web_site_real_temperature', value: 0
            column name: 'error_temperature', value: 2
            column name: 'error_sign', value: 0
            column name: 'weather_web_site_id', value: 2
        }

        insert tableName: 'weather_data', {
            column name: 'id', value: 3
            column name: 'created_at_timestamp', value: 1288323623006
            column name: 'web_site_forecast_temperature', value: -1
            column name: 'web_site_real_temperature', value: 2
            column name: 'error_temperature', value: 3
            column name: 'error_sign', value: -1
            column name: 'weather_web_site_id', value: 3
        }

        rollback {
            delete tableName: 'weather_data', where: 'id in (1,2,3)'
        }
    }
    
    changeSet id: '3', author: 'author', {
        insert tableName: 'weather_forecast', {
            column name: 'id', value: 1
            column name: 'created_at_timestamp', value: 1288323623006
            column name: 'forecast_temperature', value: 1
            column name: 'real_temperature', value: 2
            column name: 'weather_web_site_id', value: 1
        }

        insert tableName: 'weather_forecast', {
            column name: 'id', value: 2
            column name: 'created_at_timestamp', value: 1288323623006
            column name: 'forecast_temperature', value: 3
            column name: 'real_temperature', value: 2
            column name: 'weather_web_site_id', value: 2
        }

        insert tableName: 'weather_forecast', {
            column name: 'id', value: 3
            column name: 'created_at_timestamp', value: 1288323623006
            column name: 'forecast_temperature', value: -1
            column name: 'real_temperature', value: 2
            column name: 'weather_web_site_id', value: 3
        }

        rollback {
            delete tableName: 'user_security', where: 'id in (1,2,3)'
        }
    }
}