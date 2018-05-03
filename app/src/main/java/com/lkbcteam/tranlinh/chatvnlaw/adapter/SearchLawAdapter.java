package com.lkbcteam.tranlinh.chatvnlaw.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lkbcteam.tranlinh.chatvnlaw.R;
import com.lkbcteam.tranlinh.chatvnlaw.other.apihelper.response.SearchLawResponse;

import java.util.List;

/**
 * Created by tranlinh on 03/05/2018.
 */

public class SearchLawAdapter extends RecyclerView.Adapter<SearchLawAdapter.ViewHolder> {

    private List<SearchLawResponse.Article> articles;
    private SearchLawListener callback;

    public SearchLawAdapter(){

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_law, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SearchLawResponse.Article article = articles.get(position);
        holder.tvArticleTitle.setText(String.format("%s %s", article.getArticleType(),article.getNumericalSymbol()));
        holder.tvArticleDescription.setText(article.getTitle());
        holder.tvPublicDay.setText(article.getPublicDay());
        holder.tvEffectDay.setText(article.getEffectDay());
        holder.container.setOnClickListener(v -> {
            callback.onClickArticleItem(position);
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public List<SearchLawResponse.Article> getArticles() {
        return articles;
    }

    public void setArticles(List<SearchLawResponse.Article> articles) {
        this.articles = articles;
    }

    public SearchLawListener getCallback() {
        return callback;
    }

    public void setCallback(SearchLawListener callback) {
        this.callback = callback;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvArticleTitle, tvArticleDescription, tvPublicDay, tvEffectDay;
        private View container;

        public ViewHolder(View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.v_container);
            tvArticleTitle = itemView.findViewById(R.id.tv_article_title);
            tvArticleDescription = itemView.findViewById(R.id.tv_article_description);
            tvPublicDay = itemView.findViewById(R.id.tv_public_day);
            tvEffectDay = itemView.findViewById(R.id.tv_effect_day);
        }
    }
    public interface SearchLawListener{
        void onClickArticleItem(int position);
    }
}
