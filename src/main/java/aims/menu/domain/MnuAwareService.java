package aims.menu.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aims.core.infrastructure.exception.Errors.MnuErrCd;
import aims.menu.infrastructure.exception.MnuDuplicateException;
import aims.menu.infrastructure.exception.MnuNotFoundException;

/**
 * 메뉴 도메인 서비스이다.
 * 
 * @author JH
 */
@Service
public class MnuAwareService {
    /**
     * 메뉴 도메인 엔티티 레파지토리
     */
    @Autowired
    private MnuRepository mnuRepository;
    
    /**
     * 신규 메뉴를 등록한다.
     * 
     * @param mnu 메뉴
     */
    public void newSave(Mnu mnu) {
        checkDuplicate(mnu.getMnuId());
        save(mnu);
    }
    
    /**
     * 신규 메뉴를 등록한다.
     * 
     * @param mnu 메뉴
     */
    public void save(Mnu mnu) {
        mnuRepository.save(mnu);
    }
    
    /**
     * 특정 메뉴를 삭제한다.
     * 
     * @param mnu 메뉴
     */
    public void delete(Mnu mnu) {
        mnuRepository.delete(mnu);
    }
    
    /**
     * 특정 메뉴를 조회한다.
     * 
     * @param mnuId 메뉴 식별자
     * @return 메뉴
     */
    public Mnu find(String mnuId) {
        return find(mnuId, MnuErrCd.EBMNUS001.name());
    }
    
    /**
     * 특정 메뉴를 조회한다.
     * 
     * @param mnuId 메뉴 식별자
     * @param errCd 오류코드
     * @return 메뉴
     */
    public Mnu find(String mnuId, String errCd) {
        Mnu mnu = mnuRepository.findByMnuId(mnuId);
        
        if (null == mnu) {
            throw new MnuNotFoundException(errCd, new Object[] { mnuId });
        }
        
        return mnu;
    }
    
    /**
     * 동일한 객체가 존재하는지 확인한다.
     * 
     * @param mnuId 메뉴 식별자
     */
    public void checkDuplicate(String mnuId) {
        checkDuplicate(mnuId, MnuErrCd.EBMNUS002.name());
    }
    
    /**
     * 동일한 메뉴가 존재하는지 확인한다.
     * 
     * @param mnuId 메뉴 식별자
     * @param errCd 에러코드
     */
    public void checkDuplicate(final String mnuId, String errCd) {
        final Mnu mnu = mnuRepository.findByMnuId(mnuId);
        
        if (null != mnu) {
            throw new MnuDuplicateException(String.format(errCd, mnuId));
        }
    }
    
    /**
     * 변경할 새로운 메뉴 식별자와 동일한 개체가 존재하는지 확인한다.
     * 
     * @param mnuId 메뉴 식별자
     * @param newMnuId 새로운 메뉴 식별자
     */
    public void checkEditDuplicate(String mnuId, String newMnuId) {
        // 변경할 식별자와 새로운 식별자가 다른 경우, 새로운 식별자가 존재하는지 검사한다.
        if (!mnuId.equals(newMnuId)) {
            checkDuplicate(newMnuId, MnuErrCd.EBMNUS003.name());
        }
    }
    
    /**
     * 전체 메뉴 목록을 조회한다.
     * 
     * @return 메뉴 목록
     */
    public List<Mnu> findAll() {
        return mnuRepository.findAll();
    }
}
