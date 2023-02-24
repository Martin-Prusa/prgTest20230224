package cz.martin.repositories;

import cz.martin.models.Tweet;
import jakarta.enterprise.context.ApplicationScoped;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TweetsRepository {
    private String databaseURL = "jdbc:mariadb://localhost/twitter?user=root&password=password";
    public void createTweet(Tweet tweet) {
        try (
                Connection connection = DriverManager.getConnection(databaseURL);
                PreparedStatement statement = connection.prepareStatement("""
                INSERT INTO tweets (content, author, likes, created_at, updated_at)
                VALUES (?, ?, ?, NOW(), NOW())
                """);
                ) {
            statement.setString(1, tweet.getContent());
            statement.setString(2, tweet.getAuthor());
            statement.setInt(3, tweet.getLikes());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tweet> getTweets() {
        List<Tweet> tweets = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(databaseURL);
                PreparedStatement statement = connection.prepareStatement("""
                SELECT tweet_id, content, author, likes, created_at, updated_at
                FROM tweets
                """);
        ) {
            try (
                    ResultSet rs = statement.executeQuery()
                    ) {
                while (rs.next()) {
                    tweets.add(new Tweet(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getTimestamp(5).toLocalDateTime(),
                            rs.getTimestamp(6).toLocalDateTime()
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tweets;
    }

    public void deleteTweet(int id) {
        try (
                Connection connection = DriverManager.getConnection(databaseURL);
                PreparedStatement statement = connection.prepareStatement("""
                DELETE FROM tweets
                WHERE tweet_id = ?
                """);
        ) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Tweet getTweetById(int id) {
        Tweet tweet = null;

        try (
                Connection connection = DriverManager.getConnection(databaseURL);
                PreparedStatement statement = connection.prepareStatement("""
                SELECT tweet_id, content, author, likes, created_at, updated_at
                FROM tweets
                """);
        ) {
            try (
                    ResultSet rs = statement.executeQuery()
            ) {
                while (rs.next()) {
                    tweet = (new Tweet(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getInt(4),
                            rs.getTimestamp(5).toLocalDateTime(),
                            rs.getTimestamp(6).toLocalDateTime()
                    ));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tweet;
    }

    public void editTweet(Tweet tweet) {
        try (
                Connection connection = DriverManager.getConnection(databaseURL);
                PreparedStatement statement = connection.prepareStatement("""
                UPDATE tweets
                SET content = ?, author = ?, updated_at = NOW()
                WHERE tweet_id = ?
                """);
        ) {
            statement.setString(1, tweet.getContent());
            statement.setString(2, tweet.getAuthor());
            statement.setInt(3, tweet.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void like(int tweetId, int count) {
        try (
                Connection connection = DriverManager.getConnection(databaseURL);
                PreparedStatement statement = connection.prepareStatement("""
                UPDATE tweets
                SET likes = ?
                WHERE tweet_id = ?
                """);
        ) {
            statement.setInt(1, count);
            statement.setInt(2, tweetId);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
