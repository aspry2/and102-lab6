package com.codepath.articlesearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.codepath.articlesearch.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.serialization.json.Json

fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private fun replaceFragment(articleListFragment: ArticleListFragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.article_frame_layout, articleListFragment)
        fragmentTransaction.commit()


        // Define fragments
        val bookFragment: Fragment = BestSellerBooksFragment()
        val articleFragment: Fragment = ArticleListFragment()

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_nav)

        // Handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.nav_books -> fragment = bookFragment
                R.id.nav_articles -> fragment = articleFragment
            }
            fragmentManager.beginTransaction().replace(R.id.article_frame_layout, fragment).commit()
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.nav_books

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Swap the FrameLayout with the fragment
        replaceFragment(ArticleListFragment())
    }
}