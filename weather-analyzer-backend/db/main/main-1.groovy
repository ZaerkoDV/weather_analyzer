databaseChangeLog(logicalFilePath: 'main-1') {
    changeSet id: '1', author: 'author', {
        createTable tableName: 'weather_web_site', {
            column name: 'id', type: 'bigint', autoIncrement: true, {
                constraints primaryKey: true, nullable: false
            }

            column name: 'name', type: 'varchar(255)', {
                constraints nullable: false
            }

            column name: 'full_url', type: 'varchar(1024)', {
                constraints nullable: false
            }

            column name: 'last_success_connected', type: 'bigint', {
                constraints nullable: false
            }

            column name: 'reating_position', type: 'bigint', {
                constraints nullable: false
            }
        }

        createIndex tableName: 'weather_web_site', indexName: 'idx__weather_web_site__name', {
            column name: 'name'
        }

        createIndex tableName: 'weather_web_site', indexName: 'idx__weather_web_site__full_url', {
            column name: 'full_url'
        }

        createIndex tableName: 'weather_web_site', indexName: 'idx__weather_web_site__last_success_connected', {
            column name: 'last_success_connected'
        }

        createIndex tableName: 'weather_web_site', indexName: 'idx__weather_web_site__reating_position', {
            column name: 'reating_position'
        }

        rollback {
            dropTable tableName: 'weather_web_site'
        }
    }

    changeSet id: '2', author: 'author', {
        createTable tableName: 'weather_data', {
            column name: 'id', type: 'bigint', autoIncrement: true, {
                constraints primaryKey: true, nullable: false
            }

            column name: 'created_at_timestamp', type: 'bigint', {
                constraints nullable: false
            }

            column name: 'web_site_forecast_temperature', type: 'bigint', {
                constraints nullable: true
            }
			
			column name: 'web_site_real_temperature', type: 'bigint', {
                constraints nullable: true
            }

            column name: 'error_temperature', type: 'bigint', {
                constraints nullable: true
            }

            column name: 'error_sign', type: 'bigint', {
                constraints nullable: true
            }

            column name: 'weather_web_site_id', type: 'bigint', {
                constraints nullable: false
            }
        }

        createIndex tableName: 'weather_data', indexName: 'idx__weather_data__created_at_timestamp', {
            column name: 'created_at_timestamp'
        }

        createIndex tableName: 'weather_data', indexName: 'idx__weather_data__web_site_forecast_temperature', {
            column name: 'web_site_forecast_temperature'
        }
		
		createIndex tableName: 'weather_data', indexName: 'idx__weather_data__web_site_real_temperature', {
            column name: 'web_site_real_temperature'
        }

        createIndex tableName: 'weather_data', indexName: 'idx__weather_data__error_temperature', {
            column name: 'error_temperature'
        }

        createIndex tableName: 'weather_data', indexName: 'idx__weather_data__error_sign', {
            column name: 'error_sign'
        }

        createIndex tableName: 'weather_data', indexName: 'idx__weather_data__weather_web_site_id', {
            column name: 'weather_web_site_id'
        }

        rollback {
            dropTable tableName: 'weather_data'
        }
    }

    changeSet(id: '3', author: 'author') {
        addForeignKeyConstraint(
            baseColumnNames: 'weather_web_site_id',
            baseTableName: 'weather_data',
            constraintName: 'weather_web_site_fk_in_weather_data',
            deferrable: 'false',
            initiallyDeferred: 'false',
            onDelete: 'CASCADE',
            onUpdate: 'CASCADE',
            referencedColumnNames: 'id',
            referencedTableName: 'weather_web_site'
        )
    }

    changeSet id: '4', author: 'author', {
        createTable tableName: 'weather_forecast', {
            column name: 'id', type: 'bigint', autoIncrement: true, {
                constraints primaryKey: true, nullable: false
            }

            column name: 'created_at_timestamp', type: 'bigint', {
                constraints nullable: false
            }

            column name: 'forecast_temperature', type: 'bigint', {
                constraints nullable: true
            }

            column name: 'real_temperature', type: 'bigint', {
                constraints nullable: true
            }

            column name: 'weather_web_site_id', type: 'bigint', {
                constraints nullable: true
            }
        }

        createIndex tableName: 'weather_forecast', indexName: 'idx__weather_forecast__created_at_timestamp', {
            column name: 'created_at_timestamp'
        }

        createIndex tableName: 'weather_forecast', indexName: 'idx__weather_forecast__forecast_temperature', {
            column name: 'forecast_temperature'
        }

        createIndex tableName: 'weather_forecast', indexName: 'idx__weather_forecast__real_temperature', {
            column name: 'real_temperature'
        }

        createIndex tableName: 'weather_forecast', indexName: 'idx__weather_forecast__weather_web_site_id', {
            column name: 'weather_web_site_id'
        }

        rollback {
            dropTable tableName: 'weather_forecast'
        }
    }

    changeSet(id:'5', author: 'author') {
        addForeignKeyConstraint(
            baseColumnNames: 'weather_web_site_id',
            baseTableName: 'weather_forecast',
            constraintName: 'weather_web_site_fk_in_weather_forecast',
            deferrable: 'false',
            initiallyDeferred: 'false',
            onDelete: 'CASCADE',
            onUpdate: 'CASCADE',
            referencedColumnNames: 'id',
            referencedTableName: 'weather_web_site'
        )
    }
}
