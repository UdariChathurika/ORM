package com.chathu.doc.service;

import com.chathu.doc.model.*;
import com.chathu.doc.repository.DocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocServiceImpl implements DocService{
    @Autowired
    DocRepository docRepository;

    @Override
    public Document save(Document document) {

        for(Card card:document.getCard()) {
            card.setDocument(document);
            for(Page page:card.getPage()){
                page.setCard(card);
                for(Section section:page.getSection()){
                    section.setPage(page);
                    for(Paragraph paragraph:section.getParagraph()){
                        paragraph.setSection(section);
                    }
                }
            }
        }

        return docRepository.save(document);
    }
}
